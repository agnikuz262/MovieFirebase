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
import kotlinx.android.synthetic.main.fragment_all_movies.*

class AllMoviesFragment : Fragment() {

    private lateinit var viewModel: AllMoviesViewModel
    private lateinit var adapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_all_movies, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(AllMoviesViewModel::class.java)

    }

    override fun onStart() {
        super.onStart()

        viewModel.getAllMovies().observe(this,
            Observer {
                adapter = MovieAdapter(
                    it,
                    requireContext(),
                    this
                )
                recycler_all.adapter = adapter
                recycler_all.layoutManager =
                    LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
            })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        recycler_all.adapter = null
    }
}
