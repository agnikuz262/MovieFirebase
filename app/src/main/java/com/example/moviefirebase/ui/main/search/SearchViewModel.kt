package com.example.moviefirebase.ui.main.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviefirebase.model.model.movie_api.MovieApi
import com.example.moviefirebase.model.model.movie_api.MovieSearch
import com.example.moviefirebase.model.model.movie_api.MovieSearchResponse
import kotlinx.coroutines.coroutineScope
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class SearchViewModel : ViewModel() {

    companion object {
        private var searchResponse = MovieSearchResponse()
    }

    fun getSearchedMovies(): List<MovieSearch> {
        try {
            MovieApi.retrofitServie.getSearchMovies().enqueue(
                object : Callback<MovieSearchResponse> {
                    override fun onFailure(call: Call<MovieSearchResponse>, t: Throwable) {
                        Log.e("TAG", "Retrofit error $t")
                    }

                    override fun onResponse(
                        call: Call<MovieSearchResponse>,
                        response: Response<MovieSearchResponse>
                    ) {
                        searchResponse = response.body()!!
                    }
                }
            )
        } catch (e: Exception) {
            Log.e("TAG", "Error try catch $e")
        }
        return searchResponse.list!!
    }
}
