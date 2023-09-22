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
import com.ImageSearch.model.Item
import com.ImageSearch.model.SharedPreferencesUtil
import com.ImageSearch.model.SharedPreferencesUtil.saveLikedItems
import com.ImageSearch.searchPage.BookAdapter
import com.ImageSearch.viewmodel.LikeViewModel
import com.example.databinding.FragmentBoxBinding
import timber.log.Timber


class BoxFragment : Fragment() {
    lateinit var binding: FragmentBoxBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: BookAdapter
    private val likeViewModel: LikeViewModel by activityViewModels()
    private var loadedItems: List<Item> = emptyList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        likeViewModel.loadLikedItemsFromLocalStorage(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBoxBinding.inflate(inflater, container, false)

        adapter = BookAdapter(likeViewModel)
        binding.bookCyclerview.adapter = adapter
        binding.bookCyclerview.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        likeViewModel.likedItems.observe(viewLifecycleOwner) { likedItems ->

            Timber.e("자징더;ㄴ가깉ㅇ,ㅁ ${saveLikedItems(likedItems)}")
            Timber.e("여기는 데이터가 오 ㅐ 안들어오까..;. $likedItems")
            adapter.submitList(likedItems)
            saveLikedItems(likedItems)
            Timber.e("너가 범인? ${(likedItems.toString())}")

        }

        loadedItems = loadLikedItems()
        adapter.submitList(loadedItems)

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        likeViewModel.saveLikedItemsToLocalStorage(requireContext())
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        adapter.submitList(loadedItems)
    }

    private fun saveLikedItems(likedItems: List<Item>) {
        // 좋아요한 아이템 목록을 저장
        context?.let { saveLikedItems(likedItems, it) }
    }


    private fun loadLikedItems(): List<Item> {
        // 저장된 좋아요한 아이템 목록을 로드
        return context?.let { SharedPreferencesUtil.loadLikedItems(it) } ?: emptyList()
    }

}