package com.example.moviefirebase.model.model.api

import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface MovieApiClient {
    @GET(" ")
    fun getSearchMoviesAsync(): Deferred<Response<MovieSearchResponse>>
    @GET(" ")
    fun getMovieAsync(): Deferred<Response<MovieApiEntity>>
}