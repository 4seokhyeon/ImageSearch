package com.ImageSearch.storgepage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.ImageSearch.searchPage.BookAdapter
import com.ImageSearch.viewmodel.LikeViewModel
import com.example.databinding.FragmentBoxBinding
import timber.log.Timber


class BoxFragment : Fragment() {
    lateinit var binding: FragmentBoxBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: BookAdapter
    private val likeViewModel: LikeViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBoxBinding.inflate(inflater, container, false)

        adapter = BookAdapter(likeViewModel)
        binding.bookCyclerview.adapter = adapter
        binding.bookCyclerview.layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)

        likeViewModel.likedItems.observe(viewLifecycleOwner) {
            it
            Timber.e("여기는 데이터가 오 ㅐ 안들어오까..;. $it")
            adapter.submitList(it)

            Timber.e("너가 범인? ${(it.toString())}")

        }

        return binding.root
    }


}