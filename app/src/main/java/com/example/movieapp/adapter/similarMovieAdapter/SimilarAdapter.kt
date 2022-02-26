package com.example.movieapp.adapter.similarMovieAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.R
import com.example.movieapp.const.Constants
import com.example.movieapp.const.Constants.Companion.IMAGE_BASE_URL
import com.example.movieapp.moviedata.MovieData
import kotlinx.android.synthetic.main.movie_row.view.*

class SimilarAdapter : RecyclerView.Adapter<SimilarAdapter.SimilarViewHolder>() {

    private var similarList = ArrayList<MovieData>()

    inner class SimilarViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun similarMovieDetail(movie: MovieData) {
            itemView.setOnClickListener {
                listenerSimilarDetailScreen?.similarDetailScreen(movie)
            }

        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimilarViewHolder {
        return SimilarViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.similar_movie_row, parent, false)
        )
    }

    override fun onBindViewHolder(holder: SimilarViewHolder, position: Int) {

        val currentItem = similarList[position]

        val imgBaseUrl = IMAGE_BASE_URL
        val movieImg = currentItem.backdropPath

        val currentImg = imgBaseUrl + movieImg
        val imgHolder = holder.itemView.img_itemHolder
        val context = holder.itemView.context
//        Glide.with(holder.itemView.context).load(currentImg).into(imgHolder)
        holder.similarMovieDetail(currentItem)


    }

    override fun getItemCount() = similarList.size

    fun setSimilarMovieData(newList: List<MovieData>) {
        similarList = newList as ArrayList<MovieData>
        notifyDataSetChanged()
    }

    private var listenerSimilarDetailScreen: SimilarDetailScreen? = null

    interface SimilarDetailScreen {
        fun similarDetailScreen(movie: MovieData)
    }

    fun editSimilarMovieItem(listener: SimilarDetailScreen) {
        this.listenerSimilarDetailScreen = listener
    }

}