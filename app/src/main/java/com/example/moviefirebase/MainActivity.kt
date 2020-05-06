package com.example.moviefirebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.moviefirebase.model.model.firebase.MovieDB
import com.example.moviefirebase.ui.main.library.LibraryFragment
import com.example.moviefirebase.ui.main.search.SearchFragment
import com.example.moviefirebase.ui.main.settings.SettingsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.database.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    private lateinit var dbReference: DatabaseReference
    private lateinit var listOfMovies: ArrayList<MovieDB>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        val firebase = FirebaseDatabase.getInstance()
        dbReference = firebase.getReference("movies")
//        addMovie(
//            "Masło",
//            "xkjbcxhc bvjxbvx bdjbfkjz bkjzc jkzcvvcvfdfvdf fd dfg d dfgdfg dfg dfg dfg dfg dfgdfg dg dfgdfg dfgdfgfdg dfgdfg dsgd gdfgfh kkasnkjnds dj kdjjsd kjsdfj dsjfjs bjsb fjsdbjsdbf jd",
//            "https://vignette.wikia.nocookie.net/disney/images/7/77/Kubu%C5%9B_Puchatek.jpg/revision/latest/top-crop/width/360/height/450?cb=20161021130126&path-prefix=pl"
//        )
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNav.setOnNavigationItemSelectedListener { item ->
            var fragment: Fragment =
                LibraryFragment()

            when (item.itemId) {
                R.id.menu_library -> {
                    fragment =
                        LibraryFragment()
                }
                R.id.menu_search -> {
                    fragment =
                        SearchFragment()
                }
                R.id.menu_settings -> {
                    fragment =
                        SettingsFragment()
                }
            }

            supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment)
                .commit()
            true
        }

    }

    private fun addMovie(title: String, description: String, poster: String) {
        val movie = MovieDB(title, description, poster)
        dbReference.child("${Date().time}").setValue(movie)
    }


}


