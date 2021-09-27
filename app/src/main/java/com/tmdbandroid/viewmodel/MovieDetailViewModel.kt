package com.tmdbandroid.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tmdbandroid.model.data.MovieDetail
import com.tmdbandroid.model.network.NetworkResult
import com.tmdbandroid.model.repository.MovieDetailRepository
import kotlinx.coroutines.launch

class MovieDetailViewModel(private val movieRepository:MovieDetailRepository,id:String) : ViewModel() {
    val movieDetail = MutableLiveData<MovieDetail>()
    val error = MutableLiveData<String>()

    init {
        getMovieDetail(id)
    }

    fun getMovieDetail(id:String){
        viewModelScope.launch {
            when (val result = movieRepository.getMovieDetail(id)) {
                is NetworkResult.Success -> movieDetail.postValue(result.data)
                is NetworkResult.Error -> error.postValue(result.errorMessage)
            }
        }
    }

}