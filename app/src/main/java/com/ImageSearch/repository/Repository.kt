package com.ImageSearch.repository

import com.ImageSearch.api.RetrofitInstance
import com.ImageSearch.model.ImageSearchResponse
import retrofit2.Response


class Repository {

    suspend fun searchImage(query: String,sort :String): Response<ImageSearchResponse>{
        return RetrofitInstance.api.searchImage(query = query, sort = sort, page = 1,size = 5)
    }
}