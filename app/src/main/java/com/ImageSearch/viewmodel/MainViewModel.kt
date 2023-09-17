package com.ImageSearch.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ImageSearch.model.Item
import com.ImageSearch.repository.Repository
import kotlinx.coroutines.launch
import timber.log.Timber

class MainViewModel (private val repository: Repository) : ViewModel(){

    private val _rawItems = MutableLiveData<List<Item>?>() // 직접 초기화

    // 매핑된 LiveData를 반환하는 프로퍼티 정의
    val itemsLiveData: MutableLiveData<List<Item>?> = _rawItems
    constructor() : this(Repository())
    fun reloadItems(query: String) {
        viewModelScope.launch {
            try {
                val response = repository.searchImage(query, "accuracy", 1, 10)
                if (response.isSuccessful) {
                    val imageSearchResponse = response.body()
                    val items = imageSearchResponse?.documents
                    _rawItems.value = items
                    Timber.d("API 데이터가 성공적으로 받아와짐: $items")
                } else {
                    // 에러 처리 로직 추가
                }
            } catch (e: Exception) {
                // 예외 처리
                Timber.e(e, "API 데이터를 가져오는 중 오류 발생")
            }
        }
    }
}