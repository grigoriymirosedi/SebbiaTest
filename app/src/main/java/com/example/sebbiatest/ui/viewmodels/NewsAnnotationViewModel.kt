package com.example.sebbiatest.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sebbiatest.core.util.Resource
import com.example.sebbiatest.domain.model.NewsAnnotation
import com.example.sebbiatest.domain.model.NewsCategory
import com.example.sebbiatest.domain.repository.NewsRepository
import kotlinx.coroutines.launch

class NewsAnnotationViewModel(private val categoryId: Int, private val newsRepository: NewsRepository): ViewModel() {
    private val _newsAnnotation: MutableLiveData<Resource<List<NewsAnnotation>>> = MutableLiveData()
    val newsAnnotation: LiveData<Resource<List<NewsAnnotation>>>
        get() = _newsAnnotation

    var newsPage = 1

    init {
        fetchNewsAnnotationById(categoryId, newsPage)
    }

    fun fetchNewsAnnotationById(categoryId: Int, newsPage: Int) = viewModelScope.launch {
        _newsAnnotation.postValue(Resource.Loading())
        val newsResponse  = newsRepository.getCategoryNewsById(categoryId = categoryId, pageNumber = newsPage)

    }
}