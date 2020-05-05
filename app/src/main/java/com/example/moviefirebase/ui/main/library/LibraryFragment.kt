package com.example.moviefirebase.ui.main.library

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager

import com.example.moviefirebase.R
import com.example.moviefirebase.ui.main.library.tabs.AllMoviesFragment
import com.example.moviefirebase.ui.main.library.tabs.UnwatchedMoviesFragment
import com.example.moviefirebase.ui.main.library.tabs.WatchedMoviesFragment
import com.google.android.material.tabs.TabLayout

private const val ARG_OBJECT = "object"

class LibraryFragment : Fragment() {

    private lateinit var listPagerAdapter: ListPagerAdapter
    private lateinit var viewPager: ViewPager

    companion object {
        fun newInstance() =
            LibraryFragment()
    }

    private lateinit var viewModel: LibraryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.library_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listPagerAdapter =
            ListPagerAdapter(
                childFragmentManager
            )
        viewPager = view.findViewById(R.id.pager)
        viewPager.adapter = listPagerAdapter
        val tabLayout = view.findViewById<TabLayout>(R.id.tab_layout)
        tabLayout.setupWithViewPager(viewPager)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(LibraryViewModel::class.java)
        // TODO: Use the ViewModel
    }
}

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
