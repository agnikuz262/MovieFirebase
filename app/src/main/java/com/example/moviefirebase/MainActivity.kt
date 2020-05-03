package com.example.moviefirebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviefirebase.model.model.firebase.MovieDB
import com.example.moviefirebase.ui.main.MainFragment
import com.example.moviefirebase.ui.main.MovieAdapter
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.movie_list.*

class MainActivity : AppCompatActivity() {

    private lateinit var dbReference: DatabaseReference
    private lateinit var listOfMovies: ArrayList<MovieDB>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }

//        val fireBase = FirebaseDatabase.getInstance()
//        dbReference = fireBase.getReference("movies")
//        recyclerView.layoutManager = GridLayoutManager(applicationContext, 1)
//
//        dbReference.addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                listOfMovies = ArrayList()
//                for(row in snapshot.children) {
//                    val newRow = row.getValue(MovieDB::class.java)
//                    listOfMovies.add(newRow!!)
//                }
//                setupMovieAdapter(listOfMovies)
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//               Log.e("TAG", error.toString())
//            }
//        })
    }

//    private fun setupMovieAdapter(arrayData: ArrayList<MovieDB>) {
//        recyclerView.adapter = MovieAdapter(arrayData)
//    }
}
