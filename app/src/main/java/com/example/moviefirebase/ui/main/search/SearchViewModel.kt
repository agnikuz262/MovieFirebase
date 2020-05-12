package com.example.moviefirebase.ui.main.search

import androidx.lifecycle.ViewModel
import com.example.moviefirebase.model.model.movie_api.MovieSearchResponse
import com.example.moviefirebase.model.model.movie_api.MovieService

class SearchViewModel : ViewModel() {
    var searchedMovies: MovieSearchResponse? = null

    suspend fun getSearchFromApi(typedSearch: String): MovieSearchResponse {
            val apiResponse = MovieService().getApiMovies(typedSearch).getMoviesAsync().await()

            if (apiResponse.isSuccessful) {
                searchedMovies = apiResponse.body()
            }

        return searchedMovies!!
    }
}
