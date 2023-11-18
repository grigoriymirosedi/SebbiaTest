package com.example.sebbiatest.di

import com.example.sebbiatest.ui.adapters.NewsAnnotationAdapter
import dagger.Module
import dagger.Provides

@Module
class AdaptersModule {
    @Provides
    fun provideAnnotationRecyclerViewAdapter(): NewsAnnotationAdapter =
        NewsAnnotationAdapter()
}