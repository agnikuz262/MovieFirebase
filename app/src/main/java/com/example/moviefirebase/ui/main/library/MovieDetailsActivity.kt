package com.example.moviefirebase.ui.main.library

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.moviefirebase.R
import com.example.moviefirebase.model.model.firebase.MovieDbEntity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.content_movie.*
import kotlinx.android.synthetic.main.content_search_movie.*
import java.lang.Exception

class MovieDetailsActivity : AppCompatActivity() {
    private lateinit var movie: MovieDbEntity
    private val uid = FirebaseAuth.getInstance().uid
    var dbReference: DatabaseReference =
        FirebaseDatabase.getInstance().getReference("movies").child("$uid")
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

                        if (movie.type != "" && movie.type != null)
                            details_type.setText(movie.type!!.capitalize())
                        details_title.setText(movie.title)
                        details_description.setText(movie.description)
                        details_rate.setText(
                            getString(
                                R.string.rate_placeholder,
                                movie.rate
                            )
                        )
                        details_director.setText(movie.director)
                        details_actors.setText(movie.actors)
                        details_year.setText(movie.year)
                        details_genre.setText(movie.genre)
                        details_country.setText(movie.country)
                        if (movie.poster != "" && movie.poster != "N/A") {
                            Picasso.with(applicationContext).load(movie.poster)
                                .error(R.drawable.ic_movie)
                                .into(details_poster)
                        } else
                            Picasso.with(applicationContext)
                                .load(getString(R.string.default_poster_url)).error(R.drawable.ic_movie)
                                .into(details_poster)
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
            val formattedRate: String
            if (rate.contains("/10")) {
                formattedRate = rate.replace("/10", "")
            } else {
                formattedRate = rate
            }
            val type = details_type.text.toString()
            val country = details_country.text.toString()

            val updatedMovie = MovieDbEntity(
                title,
                description,
                movie.poster,
                director,
                actors,
                formattedRate,
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
