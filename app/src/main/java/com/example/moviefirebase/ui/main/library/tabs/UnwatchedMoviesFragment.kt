package com.example.moviefirebase.ui.main.library.tabs


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.moviefirebase.R

/**
 * A simple [Fragment] subclass.
 */
class UnwatchedMoviesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_unwatched_movies, container, false)
    }


}
