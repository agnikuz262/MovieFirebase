package com.example.moviefirebase.ui.main.search

import androidx.lifecycle.ViewModel
import com.example.moviefirebase.model.model.movie_api.MovieSearchResponse
import com.example.moviefirebase.model.model.movie_api.MovieService

class SearchViewModel : ViewModel() {
    fun getSearchFromApi(typedSearch: String) : MovieSearchResponse {
        return MovieService().getSearchedMovies(typedSearch)
    }
}
