package com.ImageSearch.storgepage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ImageSearch.viewmodel.BookViewModel
import com.ImageSearch.viewmodel.LikeViewModel
import com.ImageSearch.viewmodel.MainViewModel
import com.example.databinding.FragmentBoxBinding
import timber.log.Timber


class BoxFragment : Fragment() {
    private val _viewModel: BookViewModel by viewModels()
    private val viewModel:MainViewModel by viewModels()
    private val likeViewModel:LikeViewModel by viewModels()
    lateinit var binding: FragmentBoxBinding
    private var like = Boolean
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: BookAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBoxBinding.inflate(inflater,container,false)

        recyclerView= binding.bookCyclerview
        recyclerView.layoutManager = GridLayoutManager(requireContext(),2)
        Timber.e("리사이클러뷰 되는중 $recyclerView")

        adapter = BookAdapter(likeViewModel)
        Timber.e("어댑터 연결됨 $_viewModel")

        // RecyclerView에 어댑터 설정
        recyclerView.adapter = adapter

        val likedItems = likeViewModel.getLikedItems()
        adapter.submitList(likedItems)

      likeViewModel.likedItems.observe(viewLifecycleOwner){ likeItems ->
          adapter.submitList(likeItems)
      }




        return binding.root
    }


}