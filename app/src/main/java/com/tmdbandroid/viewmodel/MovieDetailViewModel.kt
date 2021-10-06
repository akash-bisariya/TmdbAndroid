package com.tmdbandroid.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tmdbandroid.model.data.MovieDetail
import com.tmdbandroid.model.network.NetworkResult
import com.tmdbandroid.model.repository.MovieDetailRepository
import dagger.assisted.Assisted
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieDetailViewModel @Inject constructor(private val movieRepository:MovieDetailRepository
//                                               , @Assisted id:String
) : ViewModel() {
    val movieDetail = MutableLiveData<MovieDetail>()
    val error = MutableLiveData<String>()

    init {
        getMovieDetail("123")
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