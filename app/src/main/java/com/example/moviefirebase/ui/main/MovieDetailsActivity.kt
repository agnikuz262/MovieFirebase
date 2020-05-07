package com.example.moviefirebase.ui.main

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.moviefirebase.R
import com.example.moviefirebase.model.model.firebase.MovieDB
import com.google.firebase.database.*
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.content_movie.*
import java.lang.Exception

class MovieDetailsActivity : AppCompatActivity() {
    private lateinit var movie: MovieDB
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
                        val firebaseMovie = dataSnapshot.getValue(MovieDB::class.java)
                        movie = firebaseMovie!!

                        details_title.text = movie.title
                        details_description.text = movie.description
                        details_rate.text = movie.rate
                        details_director.text = movie.director
                        details_actors.text = movie.actors
                        if (movie.poster != null && movie.poster != "") {
                            try {
                                Picasso.with(applicationContext).load(movie.poster).into(details_poster)
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

            deleteAlert.setPositiveButton(android.R.string.yes) { _,_ ->
                dbReference.child("$id").removeValue().addOnSuccessListener {
                    Toast.makeText(applicationContext,
                        "Movie deleted", Toast.LENGTH_SHORT).show()
                }
                this.finish()
            }

            deleteAlert.setNegativeButton("Cancel") { _, _ -> }

            deleteAlert.show()
        }
    }

}