package com.example.moviefirebase.ui.main.search

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.moviefirebase.R
import com.example.moviefirebase.ui.main.SearchAdapter
import com.google.android.gms.tasks.Tasks.await
import kotlinx.android.synthetic.main.search_fragment.*

class SearchFragment : Fragment() {

    companion object {
        fun newInstance() =
            SearchFragment()
    }

    private lateinit var viewModel: SearchViewModel
    private  lateinit var adapter : SearchAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.search_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SearchViewModel::class.java)

        val movieList = viewModel.getSearchedMovies()
        adapter = SearchAdapter(movieList, requireContext(), this)
        recycler_search.adapter = adapter
        recycler_search.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)

    }

}
