package com.example.movieapp.di

import com.example.movieapp.api.MyRetrofit
import com.example.movieapp.repository.Repository
import com.example.movieapp.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val retrofitModule = module {
    single { MyRetrofit }
}

val viewModelModule = module {
    single { Repository() }

    viewModel { MainViewModel(get()) }
}