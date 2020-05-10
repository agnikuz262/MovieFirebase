package com.example.moviefirebase.model.model.movie_api

import com.squareup.moshi.Json

class MovieSearchResponse {
    @Json(name = "Response")
    val response: String? = ""

    @Json(name = "Search")
    val list: List<MovieSearch>? = null

    val totalResults: String? = ""
}