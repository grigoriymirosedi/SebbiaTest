package com.example.sebbiatest.data.api

import com.example.sebbiatest.data.dto.NewsAnnotationItemDTO
import com.example.sebbiatest.data.dto.NewsCategoryItemDTO
import com.example.sebbiatest.data.dto.NewsPostItemDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NewsAPI {
    @GET("v1/news/categories")
    fun getNewsCategories(): Response<List<NewsCategoryItemDTO>>

    @GET("v1/news/categories/{id}/news")
    fun getCategoryNewsById(
        @Path("id") searchById: Int,
        @Query("page") pageNumber: Int = 1
    ): Response<List<NewsAnnotationItemDTO>>

    @GET("v1/news/details")
    fun getNewsDetails(
        @Query("id") newsId: Int
    ): Response<NewsPostItemDTO>
}