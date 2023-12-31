package com.ImageSearch.api

import com.ImageSearch.model.ImageSearchResponse
import com.ImageSearch.model.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface KakoApi {
    @GET("v2/search/image")
    suspend fun searchImage(
        @Header("Authorization") apiKey: String = Constants.AUTH_HEADER,
        @Query("query") query : String,
        @Query("sort") sort : String,
        @Query("page") page: Int,
        @Query("size") size: Int
        ): Response<ImageSearchResponse>
}