package com.example.sebbiatest.domain.repository

import com.example.sebbiatest.data.dto.NewsAnnotationDTO
import com.example.sebbiatest.data.dto.NewsCategoryDTO
import com.example.sebbiatest.data.dto.NewsCategoryResponseDTO
import com.example.sebbiatest.data.dto.NewsDetailsDTO
import com.example.sebbiatest.ui.fragments.NewsCategoryFragment_MembersInjector
import retrofit2.Response

interface NewsRepository {

    suspend fun getNewsCategory(): Response<NewsCategoryResponseDTO>

    suspend fun getCategoryNewsById(categoryId: Int, pageNumber: Int): Response<List<NewsAnnotationDTO>>

    suspend fun getNewsDetails(newsId: Int): Response<NewsDetailsDTO>
}