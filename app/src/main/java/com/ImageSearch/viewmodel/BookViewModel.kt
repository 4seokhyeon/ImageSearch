package com.ImageSearch.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ImageSearch.model.Item

class BookViewModel: ViewModel() {
    private val bookItems: MutableLiveData<MutableList<Item>> = MutableLiveData(mutableListOf())

    val bookItem : LiveData<MutableList<Item>> = bookItems

    fun addToBookmark(item: Item) {
        val items = bookItems.value ?: mutableListOf()
        if (!items.contains(item)) {
            items.add(item)
            bookItems.postValue(items)
        }
    }

    fun removeFromBookmark(item: Item) {
        val items = bookItems.value ?: mutableListOf()
        if (items.contains(item)) {
            items.remove(item)
            bookItems.postValue(items)
        }
    }

}