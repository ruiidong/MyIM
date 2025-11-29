package com.example.proxy

import com.example.base.models.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewService {

    @GET("it/index")
    suspend fun getNews(
        @Query("key") apiKey: String,
        @Query("page") page: Int,
        @Query("num") pageSize: Int,
    ): NewsResponse
}