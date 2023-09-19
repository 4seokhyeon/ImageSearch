package com.ImageSearch.searchPage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.util.query
import com.ImageSearch.model.Item
import com.ImageSearch.viewmodel.BookViewModel
import com.ImageSearch.viewmodel.LikeViewModel
import com.ImageSearch.viewmodel.MainViewModel
import com.example.R
import com.example.databinding.FragmentSearchBinding
import timber.log.Timber


class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding
    private lateinit var recyclerView: RecyclerView
    private val viewModel: MainViewModel by viewModels()
    private lateinit var adapter: SearchAdapter
    private val _viewModel: BookViewModel by viewModels()
    private val likeViewModel:LikeViewModel by viewModels()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentSearchBinding.inflate(inflater, container, false)

        // RecyclerView 초기화
        recyclerView = binding.recyclerView
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)

        // 어댑터 초기화
        adapter = SearchAdapter(_viewModel,likeViewModel)


        // RecyclerView에 어댑터 설정
        recyclerView.adapter = adapter


        // 뷰모델의 LiveData를 관찰하여 데이터가 변경될 때 어댑터를 업데이트
        viewModel.itemsLiveData.observe(viewLifecycleOwner, Observer { items ->
            // 데이터가 변경되었을 때 어댑터에 새로운 데이터 설정
            adapter.submitList(items)
        })

        binding.searchBtn.setOnClickListener {
            val query = binding.dialogName.text.toString()
            if(query.isNotEmpty()){
                viewModel.query = query
                viewModel.reloadItems(query)
            }
        }



        return binding.root
    }

    companion object {
        fun newInstacne() = SearchFragment()
    }


}