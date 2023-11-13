package com.example.sebbiatest.app

import android.app.Application
import com.example.sebbiatest.di.AppComponent
import com.example.sebbiatest.di.DaggerAppComponent

class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        initDagger()
    }

    private fun initDagger() {
        appComponent = DaggerAppComponent.create()
    }
}