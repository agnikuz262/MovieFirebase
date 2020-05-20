package com.example.moviefirebase.adapters

import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.moviefirebase.MainActivity
import com.example.moviefirebase.R
import com.example.moviefirebase.model.model.firebase.MovieDbEntity
import com.example.moviefirebase.model.model.movie_api.MovieSearch
import com.example.moviefirebase.model.model.movie_api.MovieService
import com.example.moviefirebase.ui.main.search.SearchDetailsActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*

class SearchAdapter(
    private val searchList: List<MovieSearch>, val context: Context
) :
    RecyclerView.Adapter<SearchAdapter.ViewHolder>() {
    private val uid = FirebaseAuth.getInstance().uid
    private val dbReference: DatabaseReference =
        FirebaseDatabase.getInstance().getReference("movies").child("$uid")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.movie_row_search, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return searchList.size
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.titleView.text = searchList[position].title
        holder.typeView.text = searchList[position].type!!.capitalize()
        holder.yearView.text = searchList[position].year
        
        if (searchList[position].poster != null && searchList[position].poster != "N/A") {
            Picasso.with(context).load(searchList[position].poster).error(R.drawable.ic_new).into(holder.posterView)
        } else
            Picasso.with(context).load(context.getString(R.string.default_poster_url)).into(holder.posterView)


        holder.addButtonView.setOnClickListener {
            GlobalScope.launch(Dispatchers.Main) {
                val movieApi = MovieService().getMovie(searchList[position].title!!)
                    .getMovieAsync().await()

                if (movieApi.isSuccessful) {
                    val movieApiEntity = movieApi.body()!!
                    val date = Date().time
                    val movie = MovieDbEntity(
                        movieApiEntity.title,
                        movieApiEntity.description,
                        movieApiEntity.poster,
                        movieApiEntity.director,
                        movieApiEntity.actors,
                        movieApiEntity.rate,
                        false,
                        date,
                        movieApiEntity.genre,
                        movieApiEntity.year,
                        movieApiEntity.type,
                        movieApiEntity.country
                    )
                    dbReference.child("$date").setValue(movie).addOnSuccessListener {
                        Toast.makeText(context, "Movie added to library", Toast.LENGTH_SHORT)
                            .show()

                        holder.addButtonView.setImageDrawable(context.getDrawable(R.drawable.ic_add_full))
                    }.addOnCanceledListener {
                        Toast.makeText(context, "Something went wrong: $it", Toast.LENGTH_SHORT)
                            .show()
                    }

                } else {
                    Log.e("TAG", "Fetching movie in SearchAdapter error")
                    Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show()
                }
            }
        }

        holder.searchRowView.setOnClickListener {

            val intent = Intent(context, SearchDetailsActivity::class.java).apply {
                putExtra("title", searchList[position].title)
            }
            (context as MainActivity).startActivity(intent)
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleView = view.findViewById<TextView>(R.id.title)
        val typeView = view.findViewById<TextView>(R.id.type)
        val yearView = view.findViewById<TextView>(R.id.year)
        val posterView = view.findViewById<ImageView>(R.id.poster_row)
        val addButtonView = view.findViewById<ImageButton>(R.id.add_button)
        val searchRowView = view.findViewById<LinearLayout>(R.id.search_row)
    }
}