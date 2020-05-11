package com.example.moviefirebase.ui.main.search

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.moviefirebase.R
import com.example.moviefirebase.adapters.SearchAdapter

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


//        val response = viewModel.getSearchFromApi("How")
//
//        adapter = SearchAdapter(response.list!!, requireContext())
//        recycler_search.adapter = adapter
//        recycler_search.layoutManager =
//            LinearLayoutManager(activity, RecyclerView.VERTICAL, false)


    }

}
