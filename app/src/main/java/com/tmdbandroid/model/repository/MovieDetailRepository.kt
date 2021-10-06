package com.tmdbandroid.model.repository

import com.tmdbandroid.model.data.MovieDetail
import com.tmdbandroid.model.network.MovieService
import com.tmdbandroid.model.network.NetworkResult
import javax.inject.Inject

class MovieDetailRepository @Inject constructor(private val client:MovieService) {

    suspend fun getMovieDetail(id: String): NetworkResult<MovieDetail> {
        return try {
            val response = client.getMovieDetail(movieId = id)
            if (response.isSuccessful)
                NetworkResult.Success(response.body() as MovieDetail)
            else
                NetworkResult.Error("Network error")
        } catch (e: Exception) {
            NetworkResult.Error("Network error")
        }
    }


}