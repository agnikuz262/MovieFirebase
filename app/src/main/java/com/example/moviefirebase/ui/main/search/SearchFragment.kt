package com.example.moviefirebase.ui.main.search

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviefirebase.R
import com.example.moviefirebase.adapters.SearchAdapter
import com.example.moviefirebase.model.model.movie_api.MovieService
import kotlinx.android.synthetic.main.search_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SearchFragment : Fragment() {

    private lateinit var viewModel: SearchViewModel
    private lateinit var adapter: SearchAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.search_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SearchViewModel::class.java)

        GlobalScope.launch(Dispatchers.Main) {
            val searchedMovies = viewModel.getSearchFromApi("Friends")
            recycler_search.layoutManager =
                LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
            adapter = SearchAdapter(searchedMovies.list!!, requireContext())
            recycler_search.adapter = adapter
        }


    }
}
