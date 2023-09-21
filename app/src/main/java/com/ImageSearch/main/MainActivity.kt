package com.ImageSearch.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ImageSearch.repository.Repository
import com.ImageSearch.viewmodel.LikeViewModel
import com.ImageSearch.viewmodel.MainViewModel
import com.example.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator
import timber.log.Timber


class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private val likeViewModel:LikeViewModel by viewModels()
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val viewPagerAdapter: ViewPagerAdapter by lazy {
        ViewPagerAdapter(this@MainActivity)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initView()
        Timber.e("dasd ${initView()}")
        likeViewModel.likedItems.observe(this, Observer { likedItems ->
            likedItems
        })

    }

    private fun initView() = with(binding) {
        // ViewPager + TabLayout
        mainViewPager.adapter = viewPagerAdapter
        TabLayoutMediator(mainTabLayout, mainViewPager) { tab, position ->
            tab.setText(viewPagerAdapter.getTitle(position))
        }.attach()
    }
}