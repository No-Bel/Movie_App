package com.example.movieapp.moviedata

import com.google.gson.annotations.SerializedName

data class Movies(
    @SerializedName("page") var page: Int? = null,
    @SerializedName("results") var results: ArrayList<MovieData> = arrayListOf(),
    @SerializedName("total_pages") var totalPages: Int? = null,
    @SerializedName("total_results") var totalResults: Int? = null
)

