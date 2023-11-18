package com.example.sebbiatest.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sebbiatest.core.util.Resource
import com.example.sebbiatest.data.dto.NewsDetailsResponseDTO
import com.example.sebbiatest.data.dto.toNewsDetails
import com.example.sebbiatest.domain.model.NewsDetails
import com.example.sebbiatest.domain.repository.NewsRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class NewsDetailsViewModel(private val newsId: Int, private val newsRepository: NewsRepository): ViewModel() {
    private val _newsDetails: MutableLiveData<Resource<NewsDetails>> = MutableLiveData()
    val newsDetails: LiveData<Resource<NewsDetails>>
        get() = _newsDetails

    init {
        fetchNewsDetails(newsId)
    }

    private fun fetchNewsDetails(newsId: Int) = viewModelScope.launch {
        _newsDetails.postValue(Resource.Loading())
        val response = newsRepository.getNewsDetails(newsId)
        _newsDetails.postValue(handleResponse(response))
    }

    private fun handleResponse(response: Response<NewsDetailsResponseDTO>): Resource<NewsDetails> {
        if(response.isSuccessful) {
            response.body()?.let { responseResult ->
                return Resource.Success(responseResult.news.toNewsDetails())
            }
        }
        return Resource.Error(response.errorBody().toString())
    }
}