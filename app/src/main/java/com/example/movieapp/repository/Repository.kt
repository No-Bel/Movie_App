package com.example.movieapp.repository

import com.example.movieapp.moviedata.Movies
import com.example.movieapp.network.api.ApiInterface
import retrofit2.Response

class Repository(private val api: ApiInterface) {

    suspend fun getMovie(): Response<Movies> {
        return api.getMovie()
    }

    suspend fun getSimilarMovieRp(id: Int): Response<Movies> {
        return api.getSimilarMovie(id)
    }
}