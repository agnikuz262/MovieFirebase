package com.example.moviefirebase.model.model.firebase

import com.google.firebase.database.IgnoreExtraProperties
import com.google.firebase.database.Exclude

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
    var genre: String? = "",
    var year: String? = ""
) {
    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "title" to title,
            "description" to description,
            "poster" to poster,
            "director" to director,
            "actors" to actors,
            "rate" to rate,
            "seen" to seen,
            "id" to id,
            "genre" to genre,
            "year" to year
        )
    }
}
