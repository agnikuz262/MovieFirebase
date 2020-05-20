package com.example.moviefirebase.ui.main.search

import androidx.lifecycle.ViewModel
import com.example.moviefirebase.model.model.api.MovieSearchResponse
import com.example.moviefirebase.model.model.api.MovieService

class SearchViewModel : ViewModel() {
    var searchedMovies: MovieSearchResponse? = null

    suspend fun getSearchFromApi(typedSearch: String): MovieSearchResponse {
        val apiResponse = MovieService().getSearchMovies(typedSearch).getSearchMoviesAsync().await()

        if (apiResponse.isSuccessful) {
            searchedMovies = apiResponse.body()
        }
        return searchedMovies!!
    }
}
