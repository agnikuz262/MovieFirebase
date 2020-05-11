package com.example.moviefirebase.ui.main.library

import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager

import com.example.moviefirebase.R
import com.example.moviefirebase.adapters.ListPagerAdapter
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.library_fragment.*


class LibraryFragment : Fragment() {

    private lateinit var listPagerAdapter: ListPagerAdapter
    private lateinit var viewPager: ViewPager

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

        fab_add.setOnClickListener {
            val intent = Intent(requireContext(), AddMovieActivity::class.java).apply {}
            this.startActivity(intent)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(LibraryViewModel::class.java)

    }
}

