package com.example.moviefirebase.model.model.movie_api

import com.google.gson.annotations.SerializedName


data class MovieApiEntity(
    @SerializedName("Actors")
    val actors: String,

    @SerializedName("Director")
    val director: String,

    @SerializedName("Genre")
    val genre: String,

    @SerializedName("Plot")
    val description: String,

    @SerializedName("Poster")
    val poster: String,

    @SerializedName("Title")
    val title: String,

    @SerializedName("Year")
    val year: String,

    @SerializedName("imdbRating")
    val rate: String
)