package com.example.sebbiatest.di

import com.example.sebbiatest.data.api.NewsAPI
import com.example.sebbiatest.data.repository.NewsRepositoryImpl
import com.example.sebbiatest.domain.repository.NewsRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun provideNewsRepository(newsAPI: NewsAPI): NewsRepository =
        NewsRepositoryImpl(newsAPI)
}