package com.example.moviefirebase.model.model.movie_api

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieSearchResponse(
    @Json(name = "Search")
    val list: List<MovieSearch>? = null
)