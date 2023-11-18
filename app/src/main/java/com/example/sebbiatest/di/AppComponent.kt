package com.example.sebbiatest.di

import com.example.sebbiatest.ui.activities.MainActivity
import com.example.sebbiatest.ui.fragments.NewsAnnotationFragment
import com.example.sebbiatest.ui.fragments.NewsCategoryFragment
import dagger.Component


@Component(modules = [NetworkModule::class, RepositoryModule::class, AdaptersModule::class])
interface AppComponent {
    fun inject(activity: MainActivity)
    fun inject(fragment: NewsCategoryFragment)
    fun inject(fragment: NewsAnnotationFragment)
}