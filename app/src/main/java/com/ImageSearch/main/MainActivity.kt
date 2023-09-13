package com.ImageSearch.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    private val viewPagerAdapter: ViewPagerAdapter by lazy {
        ViewPagerAdapter(this@MainActivity)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
    }

    private fun initView() = with(binding) {
        // ViewPager + TabLayout
        mainViewPager.adapter = viewPagerAdapter
        TabLayoutMediator(mainTabLayout, mainViewPager) { tab, position ->
            tab.setText(viewPagerAdapter.getTitle(position))
        }.attach()
    }
}