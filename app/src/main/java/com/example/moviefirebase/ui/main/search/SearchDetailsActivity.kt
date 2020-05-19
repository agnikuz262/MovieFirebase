package com.example.moviefirebase.ui.main.search

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.moviefirebase.R

import com.example.moviefirebase.model.model.movie_api.MovieApiEntity
import com.example.moviefirebase.model.model.movie_api.MovieService
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.content_search_movie.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SearchDetailsActivity : AppCompatActivity() {
    private lateinit var movie: MovieApiEntity
    private var title: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_details)

        title = intent.getStringExtra("title")!!
        GlobalScope.launch(Dispatchers.Main) {
            val movieDetails = MovieService().getMovie(title)
                .getMovieAsync().await()

            if (movieDetails.isSuccessful) {
                movie = movieDetails.body()!!

                search_details_type.setText(movie.type.capitalize())
                search_details_title.setText(title)
                search_details_description.setText(movie.description)
                search_details_year.setText(movie.year)
                if (movie.rate != "")
                    search_details_rate.setText("${movie.rate}/10")
                search_details_director.setText(movie.director)
                search_details_actors.setText(movie.actors)
                search_details_genre.setText(movie.genre)
                search_details_country.setText(movie.country)
                if (movie.poster != "") {
                    try {
                        Picasso.with(applicationContext).load(movie.poster)
                            .into(search_details_poster)
                    } catch (e: Exception) {
                        print(e)
                    }
                }
            } else {
                Log.e("TAG", "Search details error")
                Toast.makeText(applicationContext, "Something went wrong", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
