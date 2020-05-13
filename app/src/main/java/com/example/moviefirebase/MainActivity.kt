package com.example.moviefirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.example.moviefirebase.model.model.firebase.MovieDbEntity
import com.example.moviefirebase.ui.main.library.AddMovieActivity
import com.example.moviefirebase.ui.main.library.LibraryFragment
import com.example.moviefirebase.ui.main.search.SearchFragment
import com.example.moviefirebase.ui.main.settings.SettingsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.main_activity.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation_view)
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
//                R.id.menu_settings -> {
//                    fragment =
//                        SettingsFragment()
//                }
            }

            supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment)
                .commit()
            true
        }

        fab_add.setOnClickListener {
            Log.e("TAG", "on click")
            val intent = Intent(applicationContext, AddMovieActivity::class.java).apply {}
            this.startActivity(intent)
        }
    }


}


