package com.example.sebbiatest.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.sebbiatest.data.NewsAnnotationPagingResource
import com.example.sebbiatest.domain.repository.NewsRepository

class NewsAnnotationViewModel(private val categoryId: Int, private val newsRepository: NewsRepository): ViewModel() {

    val newsList = Pager(PagingConfig(5 )) {
        NewsAnnotationPagingResource(categoryId = categoryId, newsRepository = newsRepository)
    }.flow.cachedIn(viewModelScope)

}