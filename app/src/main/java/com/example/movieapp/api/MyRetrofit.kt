package com.example.movieapp.api

import com.example.movieapp.const.Constants.Companion.API_BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MyRetrofit {

    private var retrofit: Retrofit? = null

    private fun getRetro(): Retrofit {
        if (retrofit == null) {
            synchronized(this) {
               retrofit = Retrofit.Builder()
                    .baseUrl(API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
        }
        return retrofit!!
    }

    val api: ApiInterface by lazy {
        getRetro().create(ApiInterface::class.java)
    }
}