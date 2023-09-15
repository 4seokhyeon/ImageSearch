package com.ImageSearch.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.util.query
import com.ImageSearch.model.ImageSearchResponse
import com.ImageSearch.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel (private val repository: Repository) : ViewModel(){

    val myCustomPosts : MutableLiveData<Response<ImageSearchResponse>> = MutableLiveData() //초기값은 ImageSearchResponse 에서 가져옴

    fun searchImage(){
        viewModelScope.launch {
            val response = repository.searchImage("query", "accuracy")
            myCustomPosts.value = response
        }
    }

}