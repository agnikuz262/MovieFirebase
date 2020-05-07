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
import com.example.moviefirebase.ui.main.MovieAdapter
import kotlinx.android.synthetic.main.fragment_to_see_movies.*

class ToSeeMoviesFragment : Fragment() {
    private lateinit var viewModel: ToSeeMoviesViewModel
    private lateinit var adapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_to_see_movies, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ToSeeMoviesViewModel::class.java)

    }

    override fun onStart() {
        super.onStart()

        viewModel.getToSeeMovies().observe(this,
            Observer {
                adapter = MovieAdapter(it, requireContext(), this)
                recycler_to_see.adapter = adapter
                recycler_to_see.layoutManager =
                    LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
            })
    }
}