package com.example.movieapp.di

import com.example.movieapp.network.api.ApiInterface
import com.example.movieapp.network.api.DefaultParameter
import com.example.movieapp.network.retrofit.MyRetrofit
import com.example.movieapp.repository.Repository
import com.example.movieapp.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val retrofitModule = module {
    single { MyRetrofit(get()) }
}

val viewModelModule = module {
    single { Repository(get()) }

    viewModel { MainViewModel(get()) }

    single {
        return@single get<MyRetrofit>().getRetro().create(ApiInterface::class.java)
    }
    factory {
        DefaultParameter()
    }
}