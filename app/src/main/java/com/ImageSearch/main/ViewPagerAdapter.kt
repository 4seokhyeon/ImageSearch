package com.ImageSearch.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ImageSearch.searchPage.SearchFragment
import com.ImageSearch.storgepage.BoxFragment
import com.example.R

class ViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    private val list = ArrayList<TabModel>()

    init {
        list.add(
            TabModel(
                SearchFragment.newInstacne(),
                R.string.tab_contactlist
            )
        )
        list.add(
            TabModel(
                BoxFragment(),
                R.string.tab_mypage
            )
        )
    }

    fun getTitle(position: Int): Int = list[position].titleRes

    override fun getItemCount(): Int {
        return list.size
    }

    override fun createFragment(position: Int): Fragment {
        return list[position].fragment
    }
}