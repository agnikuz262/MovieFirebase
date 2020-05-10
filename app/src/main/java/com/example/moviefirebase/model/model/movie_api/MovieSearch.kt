package com.example.moviefirebase.model.model.movie_api

import com.squareup.moshi.Json

class MovieSearch {
    @Json(name = "Poster")
    val poster: String? = ""

    @Json(name = "Title")
    val title: String? = ""

    @Json(name = "Year")
    val year: String? = ""

    @Json(name = "imdbID")
    val dbId: String? = ""

    @Json(name = "Type")
    val type: String? = ""
}