package com.example.movieapp.repository

import com.example.movieapp.api.MyRetrofit
import com.example.movieapp.moviedata.Movies
import retrofit2.Response

class Repository {

    suspend fun getMovie(api: String): Response<Movies> {
        return MyRetrofit.api.getMovie(api)
    }


    suspend fun getSimilarMovieRp(id: Int, api: String): Response<Movies> {
        return MyRetrofit.api.getSimilarMovie(id,api)
    }
}