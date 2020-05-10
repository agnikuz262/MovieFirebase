package com.example.moviefirebase.model.model.firebase

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class MovieDbEntity(
    var title: String? = "",
    var description: String? = "",
    var poster: String? = "",
    var director: String? = "",
    var actors: String? = "",
    var rate: String? = "",
    var seen: Boolean? = false,
    var id: Long? = 0,
    var genre: String? = ""
)
