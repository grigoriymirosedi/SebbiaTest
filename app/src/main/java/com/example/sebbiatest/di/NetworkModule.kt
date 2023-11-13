package com.example.sebbiatest.di

import com.example.sebbiatest.core.util.Constants
import com.example.sebbiatest.data.api.NewsAPI
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.crypto.spec.GCMParameterSpec
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    fun provideGsonConverter(): Gson {
        return Gson()
    }

    @Provides
    fun provideOkHttpInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().setLevel(level = HttpLoggingInterceptor.Level.BODY)

    @Provides
    fun provideOkHttpClient(loginInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(loginInterceptor)
            .build()
    }

    @Provides
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder().baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson)).client(okHttpClient).build()

    @Provides
    fun provideAPI(retrofit: Retrofit): NewsAPI =
        retrofit.create(NewsAPI::class.java)

}