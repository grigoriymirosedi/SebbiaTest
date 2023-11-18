package com.example.sebbiatest.ui.viewmodels.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.sebbiatest.domain.repository.NewsRepository
import com.example.sebbiatest.ui.viewmodels.NewsCategoryViewModel

class NewsCategoryViewModelFactory(private val repository: NewsRepository): ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NewsCategoryViewModel(repository) as T
    }
}