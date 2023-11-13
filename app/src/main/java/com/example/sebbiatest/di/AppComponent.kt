package com.example.sebbiatest.di

import com.example.sebbiatest.ui.activities.MainActivity
import com.example.sebbiatest.ui.fragments.NewsCategoryFragment
import dagger.Component


@Component(modules = [NetworkModule::class, NetworkModule::class, RepositoryModule::class])
interface AppComponent {
    fun inject(activity: MainActivity)
    fun inject(fragment: NewsCategoryFragment)
}