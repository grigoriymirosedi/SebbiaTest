package com.example.sebbiatest.data.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import com.example.sebbiatest.core.util.Constants
import retrofit2.converter.gson.GsonConverterFactory

object NewsClient {
    private val retrofit by lazy {
        var logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).client(client).build()
    }

    fun provideNewsAPI(): NewsAPI = retrofit.create(NewsAPI::class.java)
}