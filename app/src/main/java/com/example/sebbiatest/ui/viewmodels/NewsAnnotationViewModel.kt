package com.example.sebbiatest.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sebbiatest.core.util.Resource
import com.example.sebbiatest.data.dto.NewsAnnotationResponseDTO
import com.example.sebbiatest.data.dto.toNewsAnnotation
import com.example.sebbiatest.data.dto.toNewsCategory
import com.example.sebbiatest.domain.model.NewsAnnotation
import com.example.sebbiatest.domain.model.NewsCategory
import com.example.sebbiatest.domain.repository.NewsRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class NewsAnnotationViewModel(private val categoryId: Int, private val newsRepository: NewsRepository): ViewModel() {
    private val _newsAnnotation: MutableLiveData<Resource<List<NewsAnnotation>>> = MutableLiveData()
    val newsAnnotation: LiveData<Resource<List<NewsAnnotation>>>
        get() = _newsAnnotation

    var newsPage = 0

    init {
        fetchNewsAnnotationById(categoryId, newsPage)
    }

    fun fetchNewsAnnotationById(categoryId: Int, newsPage: Int) = viewModelScope.launch {
        _newsAnnotation.postValue(Resource.Loading())
        val newsAnnotationResponse  = newsRepository.getCategoryNewsById(categoryId = categoryId, pageNumber = newsPage)
        _newsAnnotation.postValue(handleResponse(newsAnnotationResponse))
    }

    fun handleResponse(response: Response<NewsAnnotationResponseDTO>): Resource<List<NewsAnnotation>> {
        if(response.isSuccessful) {
            response.body()?.let { responseResult ->
                return Resource.Success(responseResult.list.map { it.toNewsAnnotation() })
            }
        }
        return Resource.Error(response.errorBody().toString())
    }
}