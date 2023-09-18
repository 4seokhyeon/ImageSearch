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
    var currentPage: Int = 1
    var pageSize: Int = 10
    var query: String = ""

    constructor() : this(Repository())
    fun reloadItems(query: String) {
        // currentPage를 증가시킬 때마다 pageSize를 고정된 값인 20으로 설정
        currentPage++
        pageSize = 20
        this.query = query

        viewModelScope.launch {
            try {
                val response = repository.searchImage(query, "accuracy", currentPage, pageSize)
                if (response.isSuccessful) {
                    val imageSearchResponse = response.body()
                    val newItems = imageSearchResponse?.documents ?: emptyList() // 새로운 데이터 가져오기

                    // 기존 데이터에 새 데이터 추가
                    _rawItems.value = newItems
                    Timber.d("API 데이터가 성공적으로 받아와짐: ${_rawItems.value}")
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






