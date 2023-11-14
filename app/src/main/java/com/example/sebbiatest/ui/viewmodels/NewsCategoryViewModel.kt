package com.example.sebbiatest.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sebbiatest.core.util.Resource
import com.example.sebbiatest.data.dto.NewsCategoryResponseDTO
import com.example.sebbiatest.data.dto.toNewsCategory
import com.example.sebbiatest.domain.model.NewsCategory
import com.example.sebbiatest.domain.repository.NewsRepository
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

class NewsCategoryViewModel @Inject constructor(
    private val newsRepository: NewsRepository
): ViewModel() {
    private val _newsCategory: MutableLiveData<Resource<List<NewsCategory>>> = MutableLiveData()
    val newsCategory: LiveData<Resource<List<NewsCategory>>>
        get() = _newsCategory

    init {
        fetchNewsCategory()
    }

    private fun fetchNewsCategory() = viewModelScope.launch {
        _newsCategory.postValue(Resource.Loading())
        val newsResponse = newsRepository.getNewsCategory()
        _newsCategory.postValue(handleResponse(newsResponse))
    }

    private fun handleResponse(response: Response<NewsCategoryResponseDTO>): Resource<List<NewsCategory>> {
        if(response.isSuccessful) {
            response.body()?.let { responseResult ->
                return Resource.Success(responseResult.list.map { it.toNewsCategory() })
            }
        }
        return Resource.Error(response.errorBody().toString())
    }
}