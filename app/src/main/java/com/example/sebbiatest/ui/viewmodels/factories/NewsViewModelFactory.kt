package com.example.sebbiatest.ui.viewmodels.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.sebbiatest.domain.model.NewsDetails
import com.example.sebbiatest.domain.repository.NewsRepository
import com.example.sebbiatest.ui.viewmodels.NewsAnnotationViewModel
import com.example.sebbiatest.ui.viewmodels.NewsDetailsViewModel
import java.lang.Exception

class NewsViewModelFactory(private val id: Int, private val newsRepository: NewsRepository): ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>) = when(modelClass) {
        NewsAnnotationViewModel::class.java -> NewsAnnotationViewModel(id, newsRepository)
        NewsDetailsViewModel::class.java -> NewsDetailsViewModel(id, newsRepository)
        else -> throw Exception("Unexpected ViewModel")
    } as T
}