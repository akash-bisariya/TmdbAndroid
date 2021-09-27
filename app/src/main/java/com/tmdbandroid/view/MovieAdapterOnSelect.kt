package com.tmdbandroid.view

import com.tmdbandroid.model.data.MovieData

interface MovieAdapterOnSelect {
    fun onSelectMovie(movieData: MovieData)
}