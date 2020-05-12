package com.example.moviefirebase.model.model.movie_api

import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface MovieApiClient {
    @GET(" ")
    fun getMoviesAsync(): Deferred<Response<MovieSearchResponse>>
}