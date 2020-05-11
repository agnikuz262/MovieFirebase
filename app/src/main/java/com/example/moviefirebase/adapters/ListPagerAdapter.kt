package com.example.moviefirebase.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.moviefirebase.ui.main.library.tabs.AllMoviesFragment
import com.example.moviefirebase.ui.main.library.tabs.ToSeeMoviesFragment
import com.example.moviefirebase.ui.main.library.tabs.SeenMoviesFragment


class ListPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    override fun getCount(): Int = 3

    override fun getItem(i: Int): Fragment {
        when (i) {
            0 -> return AllMoviesFragment()
            1 -> return ToSeeMoviesFragment()
            2 -> return SeenMoviesFragment()
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