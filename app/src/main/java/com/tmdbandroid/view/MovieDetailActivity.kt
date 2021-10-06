package com.tmdbandroid.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.tmdbandroid.R
import com.tmdbandroid.base.TmdbApplication
import com.tmdbandroid.base.getViewModel
import com.tmdbandroid.databinding.ActivityMovieDetailBinding
import com.tmdbandroid.model.repository.MovieDetailRepository
import com.tmdbandroid.viewmodel.MovieDetailViewModel
import kotlinx.android.synthetic.main.activity_movie_detail.*
import javax.inject.Inject

class MovieDetailActivity : AppCompatActivity() {
    @Inject
    lateinit var movieDetailViewModel: MovieDetailViewModel

    @Inject
    lateinit var movieDetailRepository: MovieDetailRepository

    override fun onCreate(savedInstanceState: Bundle?) {

        (applicationContext as TmdbApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)

        movieDetailViewModel = getViewModel { MovieDetailViewModel(movieDetailRepository
//            ,intent.extras!!.get("MovieId").toString()
        ) }
        val binding: ActivityMovieDetailBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_movie_detail)
        binding.apply {
            lifecycleOwner = this@MovieDetailActivity
            viewmodel = movieDetailViewModel
        }

        movieDetailViewModel.error.observe(this, Observer {
            Snackbar.make(top_view,"Something went wrong",Snackbar.LENGTH_LONG).show()
        })

    }




}