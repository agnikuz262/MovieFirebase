package com.example.moviefirebase.ui.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.moviefirebase.ui.main.library.tabs.AllMoviesFragment
import com.example.moviefirebase.ui.main.library.tabs.UnwatchedMoviesFragment
import com.example.moviefirebase.ui.main.library.tabs.WatchedMoviesFragment


class ListPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    override fun getCount(): Int = 3

    override fun getItem(i: Int): Fragment {
        when (i) {
            0 -> return AllMoviesFragment()
            1 -> return UnwatchedMoviesFragment()
            2 -> return WatchedMoviesFragment()
        }
        return AllMoviesFragment()
    }

    override fun getPageTitle(position: Int): CharSequence {
        when (position) {
            0 -> return "All"
            1 -> return "To see"
            2 -> return "Seen"
        }
        return "All"
    }
}