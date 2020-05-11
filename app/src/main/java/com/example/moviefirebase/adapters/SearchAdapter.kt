package com.example.moviefirebase.adapters

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.moviefirebase.R
import com.example.moviefirebase.model.model.movie_api.MovieSearch
import com.squareup.picasso.Picasso

class SearchAdapter(
    private val searchList: List<MovieSearch>, val context: Context
) :
    RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

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
        holder.typeView.text = searchList[position].type
        holder.yearView.text = searchList[position].year


        if (searchList[position].poster != null) {
            try {
                Picasso.with(context).load(searchList[position].poster).into(holder.posterView)
            } catch (e: Exception) {
                print(e)
            }
        }
        holder.addButtonView.setOnClickListener {
           //todo
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleView = view.findViewById<TextView>(R.id.title)
        val typeView = view.findViewById<TextView>(R.id.type)
        val yearView = view.findViewById<TextView>(R.id.year)
        val posterView = view.findViewById<ImageView>(R.id.poster_row)
        val addButtonView = view.findViewById<ImageButton>(R.id.add_button)
    }
}