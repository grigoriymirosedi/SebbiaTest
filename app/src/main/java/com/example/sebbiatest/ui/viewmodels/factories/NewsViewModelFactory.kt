package com.example.sebbiatest.ui.viewmodels.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.sebbiatest.domain.repository.NewsRepository
import com.example.sebbiatest.ui.viewmodels.NewsAnnotationViewModel

class NewsViewModelFactory(private val id: Int, private val newsRepository: NewsRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NewsAnnotationViewModel(id, newsRepository) as T
    }
}