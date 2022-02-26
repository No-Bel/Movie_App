package com.example.movieapp.api


import com.example.movieapp.moviedata.Movies
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


//https://api.themoviedb.org/3/tv/popular?api_key=ae20305f42c7fc7180a305e413152439&page
//https://api.themoviedb.org/3/tv/{tv_id}/similar?api_key=ae20305f42c7fc7180a305e413152439&page


interface ApiInterface {

    @GET("3/tv/popular")
    suspend fun getMovie(
        @Query("api_key") api: String
    ): Response<Movies>


    @GET("/3/tv/{tv_id}/similar")
    suspend fun getSimilarMovie(
        @Path("tv_id") movieId: Int,
        @Query("api_key") api: String
    ): Response<Movies>

}
