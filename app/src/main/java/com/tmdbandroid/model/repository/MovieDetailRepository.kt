package com.tmdbandroid.model.repository

import com.tmdbandroid.model.data.MovieDetail
import com.tmdbandroid.model.network.ApiClient
import com.tmdbandroid.model.network.MovieService
import com.tmdbandroid.model.network.NetworkResult

object MovieDetailRepository {

    private val client: MovieService =
        ApiClient.getRetrofitInstance().create(MovieService::class.java)

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