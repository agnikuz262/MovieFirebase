package com.example.moviefirebase.ui.main.library

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.moviefirebase.R
import com.example.moviefirebase.model.model.firebase.MovieDbEntity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_add_movie.*

import java.util.*

class AddMovieActivity : AppCompatActivity() {
    private val uid = FirebaseAuth.getInstance().uid
    private var dbReference: DatabaseReference =
        FirebaseDatabase.getInstance().getReference("movies").child("$uid")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_movie)

        add_movie_button.setOnClickListener {
            if (add_title.text.toString() == "")
                Toast.makeText(this, "Title is required", Toast.LENGTH_SHORT).show()
            else if (!add_poster.text.toString().contains("https://") && add_poster.text.toString() != "")
                Toast.makeText(this, "Poster url must contain \"https://\"", Toast.LENGTH_SHORT).show()
            else {
                Thread {
                    addMovie()
                }.start()
                this.finish()
            }
        }
    }

    private fun addMovie() {

        val title = add_title.text.toString()
        val year = add_year.text.toString()
        val description = add_description.text.toString()
        val director = add_director.text.toString()
        val actors = add_actors.text.toString()
        val genre = add_genre.text.toString()
        var poster = add_poster.text.toString()
        if(poster == "") poster = "poster"

        val rate = add_rate.text.toString()
        val type = add_type.text.toString()
        val country = add_country.text.toString()

        val movie = MovieDbEntity(
            title,
            description,
            poster,
            director,
            actors,
            rate,
            false,
            Date().time,
            genre,
            year,
            type,
            country
        )

        dbReference.child("${Date().time}").setValue(movie)
    }
}
