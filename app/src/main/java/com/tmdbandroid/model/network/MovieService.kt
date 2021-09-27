package com.tmdbandroid.model.network

import com.tmdbandroid.BuildConfig
import com.tmdbandroid.model.data.Movie
import com.tmdbandroid.model.data.MovieDetail
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {
    //https://api.themoviedb.org/api_key=35e3bbffac00b9ba984cf7bdf29094a8&language=en-US&page=1
    @GET("/3/movie/top_rated/")
    suspend fun getTopRatedMovies(
        @Query("api_key") api_key: String = BuildConfig.API_KEY,
        @Query("language") language: String = "",
        @Query("page") page: Int
    ):Response<Movie>

    // https://api.themoviedb.org/3/movie/278?api_key=35e3bbffac00b9ba984cf7bdf29094a8&language=en-US
    @GET("3/movie/{movie}")
    suspend fun getMovieDetail(
        @Path("movie") movieId: String,
        @Query("api_key") api_key: String = BuildConfig.API_KEY,
        @Query("language") language: String = "",
    ):Response<MovieDetail>

}