package com.example.movieapp.network.retrofit

import com.example.movieapp.const.Constants.Companion.API_BASE_URL
import com.example.movieapp.network.api.DefaultParameter
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyRetrofit(defaultParameter: DefaultParameter) {

    private var retrofit: Retrofit? = null

    private val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
        this.level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(defaultParameter)
        .addInterceptor(httpLoggingInterceptor)
        .build()

     fun getRetro(): Retrofit {
        if (retrofit == null) {
            synchronized(this) {
                retrofit = Retrofit.Builder()
                    .baseUrl(API_BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
        }
        return retrofit!!
    }
}