package com.ImageSearch.searchPage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.ImageSearch.model.Item
import com.ImageSearch.model.SharedPreferencesUtil
import com.ImageSearch.viewmodel.LikeViewModel
import com.ImageSearch.viewmodel.MainViewModel
import com.example.databinding.FragmentSearchBinding
import timber.log.Timber


class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding
    private lateinit var recyclerView: RecyclerView
    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var adapter: SearchAdapter
    private val likeViewModel: LikeViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentSearchBinding.inflate(inflater, container, false)

        // RecyclerView 초기화
        recyclerView = binding.recyclerView
        recyclerView.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        // 어댑터 초기화
        adapter = SearchAdapter(likeViewModel)
        // RecyclerView에 어댑터 설정
        recyclerView.adapter = adapter
        // 뷰모델의 LiveData를 관찰하여 데이터가 변경될 때 어댑터를 업데이트
        viewModel.itemsLiveData.observe(viewLifecycleOwner, Observer { items ->
            Timber.e("라이브 데이터 $items")
            // 데이터가 변경되었을 때 어댑터에 새로운 데이터 설정
            adapter.submitList(items)
            if (items != null) {
                saveLikedItems(items)
            }
        })
  /*      val sharedPreferences = requireContext().getSharedPreferences("Prefernces",Context.MODE_PRIVATE)
        val likedDateJson = sharedPreferences.getString("likeData","")
        val gson = Gson()
        val likedItemd:List<Item> = gson.fromJson(likedDateJson,object : TypeToken<List<Item>>() {}.type)
        adapter.submitList(likedItemd)
*/

        binding.searchBtn.setOnClickListener {
            val query = binding.dialogName.text.toString()
            if (query.isNotEmpty()) {
                viewModel.query = query
                viewModel.reloadItems(query)
            }
        }

        return binding.root
    }

    private fun saveLikedItems(likedItems: List<Item>) {
        // 좋아요한 아이템 목록을 저장
        context?.let { SharedPreferencesUtil.saveLikedItems(likedItems, it) }
    }

    companion object {
        fun newInstacne() = SearchFragment()
    }


}