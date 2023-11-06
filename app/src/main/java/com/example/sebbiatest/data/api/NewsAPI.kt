package com.example.sebbiatest.data.api

import com.example.sebbiatest.data.dto.NewsAnnotationDTO
import com.example.sebbiatest.data.dto.NewsCategoryDTO
import com.example.sebbiatest.data.dto.NewsDetailsDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NewsAPI {
    @GET("v1/news/categories")
    suspend fun getNewsCategories(): Response<List<NewsCategoryDTO>>

    @GET("v1/news/categories/{id}/news")
    suspend fun getCategoryNewsById(
        @Path("id") searchById: Int,
        @Query("page") pageNumber: Int = 1
    ): Response<List<NewsAnnotationDTO>>

    @GET("v1/news/details")
    suspend fun getNewsDetails(
        @Query("id") newsId: Int
    ): Response<NewsDetailsDTO>
}