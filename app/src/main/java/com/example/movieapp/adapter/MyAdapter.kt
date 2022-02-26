package com.example.movieapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.R
import com.example.movieapp.const.Constants.Companion.IMAGE_BASE_URL
import com.example.movieapp.moviedata.MovieData
import kotlinx.android.synthetic.main.movie_row.view.*

class MyAdapter : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {


    private var movieList = ArrayList<MovieData>()


    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun movieDetail(movie: MovieData) {
            itemView.setOnClickListener {
                listenerDetailScreen?.detailScreen(movie)

            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.movie_row, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentItem = movieList[position]

        val movieImg = currentItem.backdropPath
        val imageBaseUrl = IMAGE_BASE_URL
        //my url || image_base_url + backdrop_path
        val img = imageBaseUrl + movieImg
        //imageView
        val imgHolder = holder.itemView.img_itemHolder
        val context = holder.itemView.context
        Glide.with(context).load(img).into(imgHolder)

        holder.itemView.movie_name.text = currentItem.name
        holder.itemView.vote_average.text = currentItem.voteAverage.toString()

        holder.movieDetail(currentItem)


    }

    override fun getItemCount() = movieList.size

    fun setMovieData(newMovieList: List<MovieData>) {
        movieList = newMovieList as ArrayList<MovieData>
        notifyDataSetChanged()
    }

    private var listenerDetailScreen: DetailScreen? = null

    interface DetailScreen {
        fun detailScreen(movie: MovieData)
    }

    fun editMovieItem(listener: DetailScreen) {
        this.listenerDetailScreen = listener
    }

}