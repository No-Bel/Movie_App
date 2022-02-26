package com.example.movieapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.movieapp.databinding.ActivityMainBinding
import com.example.movieapp.fragment.HomeScreenFragment
import com.example.movieapp.fragment.MovieDetailScreenFragment


class MainActivity : AppCompatActivity(), MovieDetailScreenFragment.GoHomeScreen {


    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        homeScreen()

    }

    //first app screen
    private fun homeScreen() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, HomeScreenFragment())
            .commit()
    }


    override fun goHomeScreen() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, HomeScreenFragment())
            .commit()
    }
}