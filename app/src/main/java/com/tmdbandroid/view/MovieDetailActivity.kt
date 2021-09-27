package com.tmdbandroid.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import com.squareup.picasso.Picasso
import com.tmdbandroid.R
import com.tmdbandroid.base.getViewModel
import com.tmdbandroid.databinding.ActivityMovieDetailBinding
import com.tmdbandroid.model.repository.MovieDetailRepository
import com.tmdbandroid.viewmodel.MovieDetailViewModel

class MovieDetailActivity : AppCompatActivity() {
    lateinit var movieDetailViewModel: MovieDetailViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val movieRepository = MovieDetailRepository

        movieDetailViewModel = getViewModel { MovieDetailViewModel(movieRepository,intent.extras!!.get("MovieId").toString()) }
        val binding: ActivityMovieDetailBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_movie_detail)
        binding.apply {
            lifecycleOwner = this@MovieDetailActivity
            viewmodel = movieDetailViewModel
        }

    }




}