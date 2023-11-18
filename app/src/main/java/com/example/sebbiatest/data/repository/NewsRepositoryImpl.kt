package com.example.sebbiatest.data.repository

import com.example.sebbiatest.data.api.NewsAPI
import com.example.sebbiatest.data.dto.NewsAnnotationDTO
import com.example.sebbiatest.data.dto.NewsAnnotationResponseDTO
import com.example.sebbiatest.data.dto.NewsCategoryDTO
import com.example.sebbiatest.data.dto.NewsCategoryResponseDTO
import com.example.sebbiatest.data.dto.NewsDetailsDTO
import com.example.sebbiatest.data.dto.NewsDetailsResponseDTO
import com.example.sebbiatest.domain.repository.NewsRepository
import retrofit2.Response
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    val newsAPI: NewsAPI
): NewsRepository {

    override suspend fun getNewsCategory(): Response<NewsCategoryResponseDTO> {
        return newsAPI.getNewsCategories()
    }

    override suspend fun getCategoryNewsById(
        categoryId: Int,
        pageNumber: Int,
    ): Response<NewsAnnotationResponseDTO> {
        return newsAPI.getCategoryNewsById(categoryId, pageNumber)
    }

    override suspend fun getNewsDetails(newsId: Int): Response<NewsDetailsResponseDTO> {
        return newsAPI.getNewsDetails(newsId)
    }
}