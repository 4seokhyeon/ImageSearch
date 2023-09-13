package com.ImageSearch.searchPage

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ImageSearch.ListData
import com.example.databinding.ItemSearchBinding

class SearchAdapter(private val lists:List<ListData>): RecyclerView.Adapter<SearchAdapter.SearchHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int = lists.size

    override fun onBindViewHolder(holder: SearchHolder, position: Int) {
        TODO("Not yet implemented")
    }


    class SearchHolder(val binding: ItemSearchBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(){

        }

    }
}