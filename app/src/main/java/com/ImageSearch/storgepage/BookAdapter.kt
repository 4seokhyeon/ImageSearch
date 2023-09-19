package com.ImageSearch.storgepage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ImageSearch.model.Item

import com.ImageSearch.viewmodel.BookViewModel
import com.ImageSearch.viewmodel.LikeViewModel
import com.bumptech.glide.Glide
import com.example.R
import com.example.databinding.ItemSearchBinding
import timber.log.Timber

class BookAdapter(private val likeViewModel: LikeViewModel) :
    ListAdapter<Item, BookAdapter.BookHolder>(_ItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookHolder {
        val binding = ItemSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookHolder(binding)
    }

    override fun onBindViewHolder(holder: BookHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class BookHolder(val binding: ItemSearchBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Item) {
            binding.apply {
                textListTitle.text = item.display_sitename
                listTime.text = item.datetime

                Glide.with(itemView.context)
                    .load(item.image_url)
                    .into(imageView)


                likeBtn.setOnClickListener {
                    Timber.e("클릭도미")
                    // 아이템의 좋아요 상태 토글
                 if(item.like){
                     likeBtn.setImageResource(R.drawable.icon_fill_like)
                     likeViewModel.addToLikedItems(item)

                 }else{
                     likeBtn.setImageResource(R.drawable.icon_size)
                     likeViewModel.removeFromLikedItems(item)
                 }
                    item.like = !item.like // 아이템의 좋아요 상태 변경

                    // LiveData를 통해 UI에 변경 사항 알림

                }
            }
        }
    }
}

class _ItemDiffCallback : DiffUtil.ItemCallback<Item>() {
    override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
        return oldItem.image_url == newItem.image_url
    }

    override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
        return oldItem == newItem
    }
}