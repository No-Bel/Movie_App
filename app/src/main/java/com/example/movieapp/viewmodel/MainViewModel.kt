package com.example.movieapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.moviedata.Movies
import com.example.movieapp.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response


class MainViewModel(private val repository: Repository) : ViewModel() {

    val myResponse: MutableLiveData<Response<Movies>> = MutableLiveData()

    fun getMovie(api: String) {
        viewModelScope.launch {
            val response = repository.getMovie(api)
            myResponse.value = response
        }
    }
}