package com.example.moviefirebase.ui.main.library.tabs


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.moviefirebase.R
import com.example.moviefirebase.adapters.MovieAdapter
import kotlinx.android.synthetic.main.fragment_seen_movies.*

class SeenMoviesFragment : Fragment() {

    private lateinit var viewModel: SeenMoviesViewModel
    private lateinit var adapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_seen_movies, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SeenMoviesViewModel::class.java)

    }

    override fun onStart() {
        super.onStart()

        viewModel.getSeenMovies().observe(this,
            Observer {
                adapter = MovieAdapter(
                    it,
                    requireContext(),
                    this
                )
                recycler_seen.adapter = adapter
                recycler_seen.layoutManager =
                    LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
            })
    }
}
