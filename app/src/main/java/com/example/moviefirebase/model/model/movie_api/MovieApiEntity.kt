package com.example.moviefirebase.model.model.movie_api

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieApiEntity(
    @Json(name = "Actors")
    val actors: String,

    @Json(name = "Director")
    val director: String,

    @Json(name = "Genre")
    val genre: String,

    @Json(name = "Plot")
    val description: String,

    @Json(name = "Poster")
    val poster: String,

    @Json(name = "Title")
    val title: String,

    @Json(name = "Year")
    val year: String,

    @Json(name = "imdbRating")
    val rate: String,

    @Json(name = "Type")
    val type: String,

    @Json(name = "Country")
    val country: String
)