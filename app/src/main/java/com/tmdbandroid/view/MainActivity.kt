package com.tmdbandroid.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.tmdbandroid.R
import com.tmdbandroid.base.getViewModel
import com.tmdbandroid.model.data.MovieData
import com.tmdbandroid.model.network.NetworkResult
import com.tmdbandroid.viewmodel.MovieListingViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),MovieAdapterOnSelect {

    private lateinit var movieViewModel: MovieListingViewModel

    private var adapter: MovieAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        movieViewModel = getViewModel { MovieListingViewModel() }
        movieViewModel.getPagedListLiveData().observe(this, Observer {

            adapter!!.submitList(it)
        })

        movieViewModel.notificationListingLiveDataNetworkState.observe(this, Observer {
            pb_progress.visibility = View.GONE

        })


        rv_movie.layoutManager = LinearLayoutManager(this)
        adapter = MovieAdapter(this)
        rv_movie.adapter = adapter



    }

    override fun onSelectMovie(movieData: MovieData) {
        val intent = Intent(this, MovieDetailActivity::class.java)
        intent.putExtra("MovieId",movieData.id.toString())
        startActivity(intent)
    }
}