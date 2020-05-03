package com.example.moviefirebase.model.model.firebase

import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class MovieDB(
    var uid: String? = "",
    var title: String? = "",
    var description: String? = "",
    var poster: String? = "",
    var director: String? = "",
    var actors: String? = "",
    var rate: String? = "",
    var seen: Boolean? = false
){

    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "uid" to uid,
            "title" to title,
            "description" to description,
            "poster" to poster,
            "director" to director,
            "actors" to actors,
            "rate" to rate
        )
    }
}