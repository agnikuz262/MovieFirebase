package com.example.moviefirebase.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.moviefirebase.R
import com.example.moviefirebase.model.model.firebase.MovieDB


class MovieAdapter(private val dataArray: List<MovieDB>) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.movie_row_library, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataArray.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.titleView.text = dataArray[holder.adapterPosition].title
        holder.descriptionView.text = dataArray[holder.adapterPosition].description
        holder.rateView.text = dataArray[holder.adapterPosition].rate
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleView = view.findViewById<TextView>(R.id.title)
        val descriptionView = view.findViewById<TextView>(R.id.description)
        val rateView = view.findViewById<TextView>(R.id.rate)
    }
}
