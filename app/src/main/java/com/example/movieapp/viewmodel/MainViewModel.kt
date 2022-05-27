package com.example.movieapp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.moviedata.Movies
import com.example.movieapp.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response


class MainViewModel(private val repository: Repository) : ViewModel() {

    val myResponse: MutableLiveData<Response<Movies>> = MutableLiveData()
    val myResponse2: MutableLiveData<Response<Movies>> = MutableLiveData()

    fun getMovie() {
        viewModelScope.launch {
            val response = repository.getMovie()
            if (response.isSuccessful) {
                myResponse.value = response
            }else {
                Log.d("TAG", "getMovie: ERROR")
            }
        }
    }

    fun getSimilarMovieVm(id: Int) {
        viewModelScope.launch {
            val response = repository.getSimilarMovieRp(id)
            if (response.isSuccessful) {
                myResponse2.value = response
            } else {
                Log.d("TAG", "getSimilarMovieVm: ERROR")
            }
        }
    }
}