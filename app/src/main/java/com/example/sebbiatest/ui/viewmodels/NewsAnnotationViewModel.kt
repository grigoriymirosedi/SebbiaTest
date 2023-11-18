package com.example.sebbiatest.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.sebbiatest.core.util.Resource
import com.example.sebbiatest.data.NewsAnnotationPagingResource
import com.example.sebbiatest.data.dto.NewsAnnotationResponseDTO
import com.example.sebbiatest.data.dto.toNewsAnnotation
import com.example.sebbiatest.data.dto.toNewsCategory
import com.example.sebbiatest.domain.model.NewsAnnotation
import com.example.sebbiatest.domain.model.NewsCategory
import com.example.sebbiatest.domain.repository.NewsRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class NewsAnnotationViewModel(private val categoryId: Int, private val newsRepository: NewsRepository): ViewModel() {

    val newsList = Pager(PagingConfig(5 )) {
        NewsAnnotationPagingResource(categoryId = categoryId, newsRepository = newsRepository)
    }.flow.cachedIn(viewModelScope)
    
}