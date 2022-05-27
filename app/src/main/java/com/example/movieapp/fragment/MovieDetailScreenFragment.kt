package com.example.movieapp.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.R
import com.example.movieapp.adapter.similarMovieAdapter.SimilarAdapter
import com.example.movieapp.const.Constants.Companion.IMAGE_BASE_URL
import com.example.movieapp.databinding.FragmentMovieDetailScreenBinding
import com.example.movieapp.moviedata.MovieData
import com.example.movieapp.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieDetailScreenFragment(private val movie: MovieData) : Fragment(),
    SimilarAdapter.SimilarDetailScreen {

    private lateinit var binding: FragmentMovieDetailScreenBinding
    private lateinit var similarAdapter: SimilarAdapter
    private lateinit var similarRecyclerView: RecyclerView
    private val viewModel: MainViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieDetailScreenBinding.inflate(layoutInflater)
        val view = (binding.root)
        init()
        detailScreenInfo()
        backToHomeScreen()
        return view
    }

    private fun detailScreenInfo() {
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
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Animation
        //declare the animation
        val stImg = AnimationUtils.loadAnimation(requireContext(), R.anim.st_img)
        val btt = AnimationUtils.loadAnimation(requireContext(), R.anim.btt)
        val forBtn = AnimationUtils.loadAnimation(requireContext(), R.anim.for_btn)

        val movieName = binding.movieNameTxt
        val movieOverview = binding.overviewTxt
        val movieImg = binding.movieImage
        val sMovieRecycler = binding.similarMovieRecycler
        val backArrowBtn = binding.backArrow

        movieName.startAnimation(btt)
        movieOverview.startAnimation(btt)
        movieImg.startAnimation(stImg)
        sMovieRecycler.startAnimation(btt)
        backArrowBtn.startAnimation(forBtn)
    }

    private fun init() {
        //similar movie recycler
        similarAdapter = SimilarAdapter()
        similarRecyclerView = binding.similarMovieRecycler
        similarRecyclerView.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.HORIZONTAL, false
        )
        similarRecyclerView.adapter = similarAdapter
        similarAdapter.editSimilarMovieItem(this)

        val id = movie.id

        if (id != null) {
            viewModel.getSimilarMovieVm(id)
        }
        viewModel.myResponse2.observe(viewLifecycleOwner, Observer {
            similarAdapter.setSimilarMovieData(it.body()!!.results)
            Log.d("Res", "${it.body()}")
        })
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
        requireActivity().supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, MovieDetailScreenFragment(movie))
            .commit()
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