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
        binding.bookCyclerview.layoutManager = GridLayoutManager(context,2)

        likeViewModel.likedItems.observe(viewLifecycleOwner) {
            it
            Timber.e("여기는 데이터가 오 ㅐ 안들어오까..;. $it")
            // 좋아요를 누른 항목을 사용하여 UI를 업데이트하십시오.
            // 예: RecyclerView 어댑터에 데이터를 전달하여 좋아요를 누른 항목을 표시하십시오.
            adapter.submitList(it)

            Timber.e("너가 범인? ${(it.toString())}")

        }

        return binding.root
    }


}