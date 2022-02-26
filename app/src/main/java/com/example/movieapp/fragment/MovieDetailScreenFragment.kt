package com.example.movieapp.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.movieapp.const.Constants.Companion.IMAGE_BASE_URL
import com.example.movieapp.databinding.FragmentMovieDetailScreenBinding
import com.example.movieapp.moviedata.MovieData

class MovieDetailScreenFragment(private val movie: MovieData) : Fragment() {

    private lateinit var binding: FragmentMovieDetailScreenBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieDetailScreenBinding.inflate(layoutInflater)
        val view = (binding.root)

        //image_base_url + backdrop_path
        val backdropPath = movie.backdropPath
        val imageBaseUrl = IMAGE_BASE_URL
        val img = imageBaseUrl + backdropPath

        //set movieData in MovieScreenFragment
        val movieName = binding.movieNameTxt
        val movieOverview = binding.overviewTxt
        val movieImg = binding.movieImage


        movieName.text = movie.name
        movieOverview.text = movie.overview
        Glide.with(this).load(img).into(movieImg)



        binding.backArrow.setOnClickListener {
            listenerGoToHomeScreen?.goHomeScreen()
        }

        return view
    }

    private var listenerGoToHomeScreen: GoHomeScreen? = null

    interface GoHomeScreen {
        fun goHomeScreen()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listenerGoToHomeScreen = context as GoHomeScreen
    }

    override fun onDetach() {
        super.onDetach()
        listenerGoToHomeScreen = null
    }
}