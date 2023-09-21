package com.ImageSearch.searchPage

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

class SearchAdapter(private val likeViewModel: LikeViewModel) : ListAdapter<Item, SearchAdapter.SearchHolder>(ItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchHolder {
        val binding = ItemSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchHolder, position: Int) {
        holder.bind(getItem(position))
        Timber.e("데이터 ${getItem(position)}")
    }

    inner class SearchHolder(val binding: ItemSearchBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Item) {
            binding.apply {
                textListTitle.text = item.display_sitename
                listTime.text = item.datetime
                Glide.with(itemView.context)
                    .load(item.image_url)
                    .into(imageView)

                likeBtn.setOnClickListener {

                    // 아이템의 좋아요 상태 토글
                    val liked = !item.like
                    item.like = liked
                    if (liked) {
                        likeBtn.setImageResource(R.drawable.icon_fill_like)
                        likeViewModel.addToLikedItems(item)
                        likeViewModel.onLikedItemClicked(item)
                        Timber.e("데이터 $likeViewModel")
                        Timber.e("데이터 $item")

                    } else {
                        likeBtn.setImageResource(R.drawable.icon_size)
                        likeViewModel.removeFromLikedItems(item)

                    }
                    Timber.e("클릭도미 ${item.image_url},${item.like}")
                    notifyDataSetChanged()


                }

                // 아이템의 좋아요 상태에 따라 버튼 UI 업데이트
                if (item.like) {
                    likeBtn.setImageResource(R.drawable.icon_fill_like)
                } else {
                    likeBtn.setImageResource(R.drawable.icon_size)
                }
            }
        }

    }


    class ItemDiffCallback : DiffUtil.ItemCallback<Item>() {
        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem.image_url == newItem.image_url
        }

        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem == newItem
        }
    }
}