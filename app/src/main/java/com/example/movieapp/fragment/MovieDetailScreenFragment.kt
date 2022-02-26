package com.example.movieapp.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.R
import com.example.movieapp.adapter.similarMovieAdapter.SimilarAdapter
import com.example.movieapp.const.Constants.Companion.API_KEY
import com.example.movieapp.const.Constants.Companion.IMAGE_BASE_URL
import com.example.movieapp.databinding.FragmentMovieDetailScreenBinding
import com.example.movieapp.moviedata.MovieData
import com.example.movieapp.repository.Repository
import com.example.movieapp.viewmodel.MainViewModel
import com.example.movieapp.viewmodel.MainViewModelFactory

class MovieDetailScreenFragment(private val movie: MovieData) : Fragment(),
    SimilarAdapter.SimilarDetailScreen {

    private lateinit var binding: FragmentMovieDetailScreenBinding

    private lateinit var similarAdapter: SimilarAdapter
    private lateinit var similarRecyclerView: RecyclerView
    private lateinit var viewModel: MainViewModel
    private lateinit var repository: Repository
    private lateinit var viewModelFactory: MainViewModelFactory



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieDetailScreenBinding.inflate(layoutInflater)
        val view = (binding.root)

        backToHomeScreen()

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


        //similar movie recycler
        similarAdapter = SimilarAdapter()
        similarRecyclerView = binding.similarMovieRecycler
        similarRecyclerView.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.HORIZONTAL, false
        )
        similarRecyclerView.adapter = similarAdapter
        similarAdapter.editSimilarMovieItem(this)



        repository = Repository()
        viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
        viewModel.getMovie(API_KEY)
        viewModel.myResponse.observe(viewLifecycleOwner, Observer {
            similarAdapter.setSimilarMovieData(it.body()!!.results)
            Log.d("Res", "${it.body()}")

        })


        return view
    }


    private fun backToHomeScreen() {
        binding.backArrow.setOnClickListener {
            listenerGoToHomeScreen?.goHomeScreen()
        }
    }

    private var listenerGoToHomeScreen: GoHomeScreen? = null

    interface GoHomeScreen {
        fun goHomeScreen()
    }


    override fun similarDetailScreen(movie: MovieData) {
        fragmentManager
            ?.beginTransaction()
            ?.replace(R.id.fragment_container, MovieDetailScreenFragment(movie))
            ?.commit()
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