package com.example.moviefirebase.adapters

import android.content.Context
import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.moviefirebase.MainActivity
import com.example.moviefirebase.R
import com.example.moviefirebase.model.model.firebase.MovieDbEntity
import com.example.moviefirebase.ui.main.MovieDetailsActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.squareup.picasso.Picasso
import java.lang.Exception


class MovieAdapter(
    private val movieList: List<MovieDbEntity>, val context: Context,
    val fragment: Fragment
) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    private val dbReference: DatabaseReference = FirebaseDatabase.getInstance().getReference("movies")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.movie_row_library, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.titleView.text = movieList[position].title
        holder.descriptionView.text = movieList[position].description
        if (movieList[position].rate != null && movieList[position].rate != "") {
            holder.rateView.text = movieList[position].rate + "/10"
        }

        when (movieList[position].seen) {
            true -> {
                holder.seenButtonView.setImageDrawable(context.getDrawable(R.drawable.ic_fav_check_full))
            }
            else -> {
                holder.seenButtonView.setImageDrawable(context.getDrawable(R.drawable.ic_fav_check_empty))
            }
        }
        if (movieList[position].poster != null) {
            try {
                Picasso.with(context).load(movieList[position].poster).into(holder.posterView)
            } catch (e: Exception) {
                print(e)
            }
        }
        holder.seenButtonView.setOnClickListener {
            if (movieList[position].seen == true) {
                dbReference.child("${movieList[position].id}").child("seen").setValue(false)
                    .addOnSuccessListener {
                        Toast.makeText(context, "Movie removed from \"Seen\"", Toast.LENGTH_SHORT)
                            .show()
                    }
            } else {
                dbReference.child("${movieList[position].id}").child("seen").setValue(true)
                    .addOnSuccessListener {
                        Toast.makeText(context, "Movie added to \"Seen\"", Toast.LENGTH_SHORT)
                            .show()
                    }
            }

        }

        holder.movieRowView.setOnClickListener {
            val intent = Intent(context, MovieDetailsActivity::class.java).apply {
                putExtra("id", movieList[position].id)
            }
            (context as MainActivity).startActivity(intent)
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleView = view.findViewById<TextView>(R.id.title)
        val descriptionView = view.findViewById<TextView>(R.id.description)
        val rateView = view.findViewById<TextView>(R.id.rate)
        val posterView = view.findViewById<ImageView>(R.id.poster_row)
        val movieRowView = view.findViewById<LinearLayout>(R.id.movie_row)
        val seenButtonView = view.findViewById<ImageButton>(R.id.seen_button)
    }
}
