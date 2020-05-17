package com.example.moviefirebase.ui.main

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.moviefirebase.R
import com.example.moviefirebase.model.model.firebase.MovieDbEntity
import com.google.firebase.database.*
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.content_movie.*
import java.lang.Exception

class MovieDetailsActivity : AppCompatActivity() {
    private lateinit var movie: MovieDbEntity
    var dbReference: DatabaseReference = FirebaseDatabase.getInstance().getReference("movies")
    private var id: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        Thread {
            id = intent.getLongExtra("id", -1)
            dbReference.child("$id").addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    if (dataSnapshot.exists()) {
                        val firebaseMovie = dataSnapshot.getValue(MovieDbEntity::class.java)
                        movie = firebaseMovie!!

                        details_title.setText(movie.title)
                        details_description.setText(movie.description)
                        details_rate.setText(movie.rate + "/10")
                        details_director.setText(movie.director)
                        details_actors.setText(movie.actors)
                        details_year.setText(movie.year)
                        details_genre.setText(movie.genre)
                        if (movie.poster != null && movie.poster != "") {
                            try {
                                Picasso.with(applicationContext).load(movie.poster)
                                    .into(details_poster)
                            } catch (e: Exception) {
                                print(e)
                            }
                        }
                    }
                }

                override fun onCancelled(dataSnapshot: DatabaseError) {
                    print(dataSnapshot.toException())
                }
            })
        }.start()

        details_delete_button.setOnClickListener {

            val deleteAlert = AlertDialog.Builder(this)
            deleteAlert.setTitle("Delete movie")
            deleteAlert.setMessage("Are you sure you want to delete this movie?")

            deleteAlert.setPositiveButton(android.R.string.yes) { _, _ ->
                dbReference.child("$id").removeValue().addOnSuccessListener {
                    Toast.makeText(
                        applicationContext,
                        "Movie deleted", Toast.LENGTH_SHORT
                    ).show()
                }
                this.finish()
            }

            deleteAlert.setNegativeButton("Cancel") { _, _ -> }

            deleteAlert.show()
        }

        details_save_button.setOnClickListener {
            val title = details_title.text.toString()
            val year = details_year.text.toString()
            val description = details_description.text.toString()
            val director = details_director.text.toString()
            val actors = details_actors.text.toString()
            val genre = details_genre.text.toString()
            //todo val poster = add_poster.text.toString()
            val rate = details_rate.text.toString()
            val type = details_type.text.toString()
            val country = details_country.text.toString()

            val updatedMovie = MovieDbEntity(
                title,
                description,
                movie.poster,
                director,
                actors,
                rate,
                movie.seen,
                movie.id,
                genre,
                year,
                type,
                country
            )
            val postMovie = updatedMovie.toMap()
            dbReference.child("$id").updateChildren(postMovie)

            Toast.makeText(applicationContext, "Movie updated", Toast.LENGTH_SHORT).show()
        }
    }
}
