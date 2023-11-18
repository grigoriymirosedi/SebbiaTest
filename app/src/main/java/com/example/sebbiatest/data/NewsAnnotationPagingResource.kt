package com.example.sebbiatest.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.sebbiatest.data.dto.toNewsAnnotation
import com.example.sebbiatest.domain.model.NewsAnnotation
import com.example.sebbiatest.domain.repository.NewsRepository
import retrofit2.HttpException
import javax.inject.Inject

class NewsAnnotationPagingResource @Inject constructor(
    private val newsRepository: NewsRepository,
    private val categoryId: Int,
) : PagingSource<Int, NewsAnnotation>() {
    override fun getRefreshKey(state: PagingState<Int, NewsAnnotation>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, NewsAnnotation> {
        try {
            val pageNumber = params.key ?: 0
            val response =
                newsRepository.getCategoryNewsById(categoryId = categoryId, pageNumber = pageNumber)

            if (response.isSuccessful) {
                val newsAnnotations = response.body()!!.list.map { it.toNewsAnnotation() }
                val nextPageNumber = if (newsAnnotations.isEmpty()) null else pageNumber + 1
                val prevPageNumber = if (pageNumber > 1) pageNumber - 1 else null
                return LoadResult.Page(newsAnnotations, prevPageNumber, nextPageNumber)
            } else {
                return LoadResult.Error(HttpException(response))
            }
        } catch (e: HttpException) {
            return LoadResult.Error(e)
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    companion object {
        const val INITIAL_PAGE_NUMBER = 0
    }
}
