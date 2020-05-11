package com.example.moviefirebase.model.model.movie_api

import com.google.gson.annotations.SerializedName


class MovieSearch {
    @SerializedName("Poster")
    val poster: String? = ""

    @SerializedName("Title")
    val title: String? = ""

    @SerializedName("Year")
    val year: String? = ""

    @SerializedName("imdbID")
    val dbId: String? = ""

    @SerializedName("Type")
    val type: String? = ""
}