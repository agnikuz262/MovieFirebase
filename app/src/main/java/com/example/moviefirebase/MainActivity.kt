package com.example.moviefirebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviefirebase.model.model.firebase.MovieDB
import com.example.moviefirebase.ui.main.MainFragment
import com.example.moviefirebase.ui.main.MovieAdapter
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.main_activity.*
import kotlinx.android.synthetic.main.movie_list.*
import java.sql.Time
import java.util.*
import kotlin.collections.ArrayList

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
        val firebase = FirebaseDatabase.getInstance()
        dbReference = firebase.getReference("movies")
        addMovie("Kubuś Puchatek", "Film o przygodach Kubusia Puchatka")

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
    private fun addMovie(title: String, description: String) {
        val movie = MovieDB(title, description)
        dbReference.child("${Date().time}").setValue(movie)
    }

//    private fun addUserChangeListener() {
//        dbReference.child(userId).addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(dataSnapshot: DataSnapshot) {
//                val movie = dataSnapshot.getValue(MovieDB::class.java)
//                if (movie == null) {
//                    return
//                }
//
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                // Failed to read value
//            }
//        })
//    }
}
