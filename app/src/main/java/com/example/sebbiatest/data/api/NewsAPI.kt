package com.example.sebbiatest.data.api

import com.example.sebbiatest.data.dto.NewsAnnotationDTO
import com.example.sebbiatest.data.dto.NewsAnnotationResponseDTO
import com.example.sebbiatest.data.dto.NewsDetailsDTO
import com.example.sebbiatest.data.dto.NewsCategoryResponseDTO
import com.example.sebbiatest.data.dto.NewsDetailsResponseDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NewsAPI {
    @GET("v1/news/categories")
    suspend fun getNewsCategories(): Response<NewsCategoryResponseDTO>

    @GET("v1/news/categories/{id}/news")
    suspend fun getCategoryNewsById(
        @Path("id") searchById: Int,
        @Query("page") pageNumber: Int = 0
    ): Response<NewsAnnotationResponseDTO>

    @GET("v1/news/details")
    suspend fun getNewsDetails(
        @Query("id") newsId: Int
    ): Response<NewsDetailsResponseDTO>
}