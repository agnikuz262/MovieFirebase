package com.example.moviefirebase.model.model.movie_api

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


class MovieService {
    private val apiKey = "b8157f77"
    private val baseUrl = "https://www.omdbapi.com/?apikey=$apiKey&s="

    fun getApiMovies(typedSearch: String): MovieApiClient {

        val searchWords = typedSearch.split(" ".toRegex())
        var url = baseUrl
        for (word in searchWords)
            url += "$word&"

        val retrofit =
            Retrofit.Builder().baseUrl(url).addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory()).build()
        return retrofit.create(MovieApiClient::class.java)
    }

}