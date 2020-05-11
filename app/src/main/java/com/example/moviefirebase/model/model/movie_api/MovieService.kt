package com.example.moviefirebase.model.model.movie_api

import android.util.Log
import com.google.gson.GsonBuilder
import okhttp3.*
import java.io.IOException
import java.lang.Exception

class MovieService {

    companion object {
        private var searchResponse = MovieSearchResponse()
    }

    private val apiKey = "b8157f77"
    private val baseUrl = "https://www.omdbapi.com/?apikey=$apiKey&"

    fun getSearchedMovies(typedSearch: String): MovieSearchResponse {
        val searchWords = typedSearch.split(" ".toRegex())
        var url = baseUrl + typedSearch
        for (i in searchWords)
            url += "$i&"

        try {
            val client = OkHttpClient()
            val request = Request.Builder().url(url).get().build()

            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    call.cancel()
                    Log.e("Failure: ", e.toString())
                }

                override fun onResponse(call: Call, response: Response) {
                    val successResponse = response.body()?.string()
                    val gson = GsonBuilder().setLenient().create()
                    val data = gson.fromJson(successResponse, MovieSearchResponse::class.java)
                    searchResponse = data
                }
            })

        } catch (e: Exception) {
            Log.e("TAG", "Failure of API search $e")
        }
        return searchResponse
    }
}

