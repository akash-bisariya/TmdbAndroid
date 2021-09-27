package com.tmdbandroid.model.data

data class Movie(
        val page: Int,
        val results: List<MovieData>,
        val total_pages: Int,
        val total_results: Int
)