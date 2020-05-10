package com.example.moviefirebase.model.model.movie_api

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private val apiKey = "b8157f77"
private val baseUrl = "https://www.omdbapi.com/?apikey=$apiKey&"

private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

private val retrofit =
    Retrofit.Builder().addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(baseUrl).build()

interface MovieService {
    @GET("?apiKey=b8157f77&s=How")
    fun getSearchMovies():
            Call<MovieSearchResponse>
}

object MovieApi {
    val retrofitServie: MovieService by lazy {
        retrofit.create(MovieService::class.java)
    }
}