package com.example.moviefirebase.model.model.movie_api

import com.google.gson.annotations.SerializedName


class MovieSearchResponse {
    @SerializedName("Response")
    val response: String? = ""

    @SerializedName("Search")
    val list: List<MovieSearch>? = null

    val totalResults: String? = ""
}