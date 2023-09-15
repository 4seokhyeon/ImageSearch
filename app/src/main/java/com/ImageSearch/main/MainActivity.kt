package com.ImageSearch.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.room.Room
import com.ImageSearch.model.ImageItem
import com.ImageSearch.repository.Repository
import com.ImageSearch.viewmodel.MainViewModel
import com.ImageSearch.viewmodel.MainViewModelFactory
import com.example.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator
import timber.log.Timber


class MainActivity : AppCompatActivity() {
    private lateinit var viewModel:MainViewModel
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val viewPagerAdapter: ViewPagerAdapter by lazy {
        ViewPagerAdapter(this@MainActivity)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initView()

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this,viewModelFactory).get(MainViewModel::class.java)

        binding.exButton.setOnClickListener {
            viewModel.searchImage()
        }



        viewModel.myCustomPosts.observe(this, Observer { result ->
            if(result.isSuccessful){
                Timber.d( "$result")
                for(i in result.body()!!.documents!!){
                    Timber.d( "$i")
                }
                binding.textView.text = result.body()!!.documents?.get(0)!!.image_url
            }
            else{
                Timber.d( "fail")
            }
        })

    }



    private fun adapterReasource(){

    }

    private fun initView() = with(binding) {
        // ViewPager + TabLayout
        mainViewPager.adapter = viewPagerAdapter
        TabLayoutMediator(mainTabLayout, mainViewPager) { tab, position ->
            tab.setText(viewPagerAdapter.getTitle(position))
        }.attach()
    }
}