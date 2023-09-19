package com.ImageSearch.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ImageSearch.model.Item

class LikeViewModel:ViewModel() {

    val _likedItems: MutableLiveData<MutableList<Item>> = MutableLiveData(mutableListOf())
    val likedItems: LiveData<MutableList<Item>> = _likedItems

    fun addToLikedItems(item: Item) {
        val items = _likedItems.value ?: mutableListOf()
        if (!items.contains(item)) {
            items.add(item)
            _likedItems.value = items
        }
    }

    fun removeFromLikedItems(item: Item) {
        val items = _likedItems.value ?: mutableListOf()
        if (items.contains(item)) {
            items.remove(item)
            _likedItems.value = items
        }
    }

    fun getLikedItems(): List<Item> {
        return _likedItems.value ?: emptyList()
    }
}