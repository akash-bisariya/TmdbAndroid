package com.tmdbandroid.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.tmdbandroid.BuildConfig
import com.tmdbandroid.R
import com.tmdbandroid.model.data.MovieData
import kotlinx.android.synthetic.main.row_movie.view.*

class MovieAdapter(
    val movieAdapterOnSelect: MovieAdapterOnSelect
) :
    PagedListAdapter<MovieData, MovieAdapter.CategoryViewHolder>((MovieDiffUtilCallBack())) {
    lateinit var movieData: MovieData

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(parent, R.layout.row_movie)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        movieData = getItem(position) as MovieData
        holder.bind(movieData)
    }

    inner class CategoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        constructor(parent: ViewGroup, res: Int) : this(
            LayoutInflater.from(parent.context).inflate(
                res, parent, false
            )
        )

        fun bind(movieData: MovieData) {

            Picasso.get()
                .load(BuildConfig.BASE_URL_IMAGE + movieData.poster_path)
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(itemView.iv_movie)

            itemView.tv_movie_title.text = movieData.title
            itemView.tv_movie_rating.text = movieData.vote_average.toString()
            itemView.tv_overview.text = movieData.overview

            itemView.setOnClickListener {
                movieAdapterOnSelect.onSelectMovie(movieData)
            }
        }
    }


}