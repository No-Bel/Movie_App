package com.example.movieapp.network.api

import com.example.movieapp.const.Constants.Companion.API_KEY
import okhttp3.Interceptor
import okhttp3.Response

class DefaultParameter: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val originalHttpUrl = original.url

        val url = originalHttpUrl.newBuilder()
            .addQueryParameter("api_key", API_KEY)
            .build()

        val requestBuilder = original.newBuilder()
            .url(url)
        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}