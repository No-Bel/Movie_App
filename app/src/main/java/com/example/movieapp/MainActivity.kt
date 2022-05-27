package com.example.movieapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.movieapp.databinding.ActivityMainBinding
import com.example.movieapp.fragment.HomeScreenFragment
import com.example.movieapp.fragment.MovieDetailScreenFragment
import com.example.movieapp.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity(), MovieDetailScreenFragment.GoHomeScreen {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        homeScreen()
    }

    //app first screen
    private fun homeScreen() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, HomeScreenFragment())
            .commit()
        Log.d("TAG", "homeScreen: ")
    }

    override fun goHomeScreen() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, HomeScreenFragment())
            .commit()
        Log.d("TAG", "goHomeScreen: ")
    }

    //native back press
    override fun onBackPressed() {
        when(supportFragmentManager.findFragmentById(R.id.fragment_container)){
            is HomeScreenFragment -> {
                finish()
            }
            is MovieDetailScreenFragment -> {
                goHomeScreen()
            }
        }
    }
}