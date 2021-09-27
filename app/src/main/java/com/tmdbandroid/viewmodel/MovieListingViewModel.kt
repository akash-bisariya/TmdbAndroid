package com.tmdbandroid.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.tmdbandroid.model.data.MovieData
import com.tmdbandroid.model.network.NetworkResult
import com.tmdbandroid.view.MovieListingDataSource
import kotlinx.coroutines.Dispatchers


class MovieListingViewModel() : ViewModel() {

    var notificationListingLiveDataNetworkState = MutableLiveData<NetworkResult<Boolean>>()

    private var productLiveData: LiveData<PagedList<MovieData>>

    init {
        val config = PagedList.Config.Builder()
            .setPageSize(10)
            .setInitialLoadSizeHint(10)
            .setEnablePlaceholders(false)
            .build()

        val dataSourceFactory = object : DataSource.Factory<String, MovieData>() {
            override fun create(): DataSource<String, MovieData> {
                return MovieListingDataSource(
                    Dispatchers.IO,
                    notificationListingLiveDataNetworkState
                )
            }
        }

        productLiveData = (LivePagedListBuilder(dataSourceFactory, config)).build()

    }

    fun getPagedListLiveData(): LiveData<PagedList<MovieData>> {
        return productLiveData
    }

}