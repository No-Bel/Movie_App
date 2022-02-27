package com.example.movieapp.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.adapter.MyAdapter
import com.example.movieapp.const.Constants.Companion.API_KEY
import com.example.movieapp.databinding.FragmentHomeScreenBinding
import com.example.movieapp.moviedata.MovieData
import com.example.movieapp.repository.Repository
import com.example.movieapp.viewmodel.MainViewModel
import com.example.movieapp.viewmodel.MainViewModelFactory

class HomeScreenFragment : Fragment(), MyAdapter.DetailScreen {

    private lateinit var binding: FragmentHomeScreenBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var myAdapter: MyAdapter
    private lateinit var myRecyclerView: RecyclerView
    private lateinit var repository: Repository
    private lateinit var viewModelFactory: MainViewModelFactory


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeScreenBinding.inflate(layoutInflater)

        val view = (binding.root)

        myAdapter = MyAdapter()
        myRecyclerView = binding.movieRecyclerView
        myRecyclerView.adapter = myAdapter
        myRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        myAdapter.editMovieItem(this)

        repository = Repository()
        viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]

        viewModel.getMovie(API_KEY)
        viewModel.myResponse.observe(viewLifecycleOwner, Observer {
            myAdapter.setMovieData(it.body()!!.results)
            Log.d("Response", "${it.body()}")
        })

        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Animation
        //declare the animation
        val btt = AnimationUtils.loadAnimation(requireContext(),R.anim.btt)
        val movieRecycler = binding.movieRecyclerView

        movieRecycler.startAnimation(btt)
    }

    override fun detailScreen(movie: MovieData) {
        fragmentManager
            ?.beginTransaction()
            ?.replace(R.id.fragment_container, MovieDetailScreenFragment(movie))
            ?.commit()
    }
}

