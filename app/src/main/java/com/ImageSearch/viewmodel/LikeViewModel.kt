package com.ImageSearch.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ImageSearch.model.Item
import timber.log.Timber

class LikeViewModel:ViewModel() {

    private val _likedItems: MutableLiveData<MutableList<Item>> = MutableLiveData(mutableListOf())
    val likedItems: LiveData<MutableList<Item>>
        get() = _likedItems


    private val _likedItemClicked: MutableLiveData<Item> = MutableLiveData()
    val likedItemClicked: LiveData<Item>
        get() = _likedItemClicked

    fun addToLikedItems(item: Item) {
        Timber.e("좋아요 데이터 변경 $item")
        val items = _likedItems.value ?: mutableListOf()
        if (!items.contains(item)) {
            items.add(item)
            _likedItems.postValue(items)
        }
    }

    fun removeFromLikedItems(item: Item) {
        Timber.e("좋아요 해제 $item")
        val items = _likedItems.value ?: mutableListOf()
        if (items.contains(item)) {
            items.remove(item)
            _likedItems.postValue(items)
        }
    }

    fun onLikedItemClicked(item: Item) {
        _likedItemClicked.value = item
        Timber.e("여기도 데이터 $item")
    }
}