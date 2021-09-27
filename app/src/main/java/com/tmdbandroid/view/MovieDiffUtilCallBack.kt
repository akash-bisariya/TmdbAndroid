package com.tmdbandroid.view

import androidx.recyclerview.widget.DiffUtil
import com.tmdbandroid.model.data.MovieData

class  MovieDiffUtilCallBack : DiffUtil.ItemCallback<MovieData>() {
    override fun areItemsTheSame(oldItem: MovieData, newItem: MovieData): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MovieData, newItem: MovieData): Boolean {
        return oldItem.id == newItem.id
    }

}