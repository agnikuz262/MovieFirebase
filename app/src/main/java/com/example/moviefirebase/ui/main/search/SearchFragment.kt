package com.example.moviefirebase.ui.main.search

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.AsyncTask
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviefirebase.MainActivity
import com.example.moviefirebase.R
import com.example.moviefirebase.adapters.SearchAdapter
import com.example.moviefirebase.model.model.api.MovieSearchResponse
import com.example.moviefirebase.ui.main.authentication.SignInActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.search_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL


class SearchFragment : Fragment() {

    private lateinit var viewModel: SearchViewModel
    private lateinit var adapter: SearchAdapter
    private var searchedMovies: MovieSearchResponse? = null
    private var isConnection: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.search_fragment, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SearchViewModel::class.java)
        CheckConnection().execute()
        search_button.setOnClickListener {
            val typedSearch = search_field.text.toString()
            if (typedSearch == "") {
                Toast.makeText(requireContext(), "You have to enter something", Toast.LENGTH_SHORT)
                    .show()
            } else {
                if (isConnection) {
                    GlobalScope.launch(Dispatchers.Main) {
                        searchedMovies = viewModel.getSearchFromApi(typedSearch)
                        if (searchedMovies!!.list == null) {
                            Toast.makeText(
                                requireContext(),
                                "Something went wrong",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            recycler_search.layoutManager =
                                LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
                            adapter = SearchAdapter(searchedMovies!!.list!!, requireContext())
                            recycler_search.adapter = adapter

                            val inputManager: InputMethodManager =
                                requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                            inputManager.hideSoftInputFromWindow(
                                requireActivity().currentFocus!!.windowToken,
                                InputMethodManager.HIDE_NOT_ALWAYS
                            )
                        }
                    }
                } else
                    Toast.makeText(
                        requireContext(),
                        "Something went wrong. Check Internet connection.",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                val inputManager: InputMethodManager =
                    requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputManager.hideSoftInputFromWindow(
                    requireActivity().currentFocus!!.windowToken,
                    InputMethodManager.HIDE_NOT_ALWAYS
                )

            }
        }

        logout_button.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(requireContext(), SignInActivity::class.java)
            startActivity(intent)
            (context as MainActivity).finish()
        }
    }

    inner class CheckConnection : AsyncTask<Void, Void, String>() {
        @RequiresApi(Build.VERSION_CODES.M)
        override fun doInBackground(vararg params: Void?): String {
            val cm =
                requireContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork = cm.activeNetwork
            if (activeNetwork != null) {
                try {
                    val url = URL("https://www.google.com/")
                    val urlc: HttpURLConnection = url.openConnection() as HttpURLConnection
                    urlc.setRequestProperty("User-Agent", "test")
                    urlc.setRequestProperty("Connection", "close")
                    urlc.connectTimeout = 1000 // mTimeout is in seconds
                    urlc.connect()
                    if (urlc.responseCode == 200)
                        isConnection = true
                    else
                        isConnection = false
                } catch (e: IOException) {
                    Log.i("warning", "Error checking internet connection", e)
                }
            }
            return "Connection checked"
        }

    }
}
