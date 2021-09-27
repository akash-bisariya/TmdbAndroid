package com.tmdbandroid.view

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.tmdbandroid.model.data.MovieData
import com.tmdbandroid.model.network.ApiClient
import com.tmdbandroid.model.network.MovieService
import com.tmdbandroid.model.network.NetworkResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class MovieListingDataSource(
    coroutineContext: CoroutineContext,
    private val notificationLiveDataNetworkState: MutableLiveData<NetworkResult<Boolean>>
) :
    PageKeyedDataSource<String, MovieData>() {
    private val movieService = ApiClient.getRetrofitInstance().create(MovieService::class.java)
    private val job = Job()
    private var maxPageCount: Int = 0
    private val scope = CoroutineScope(coroutineContext + job)

    override fun loadInitial(
        params: LoadInitialParams<String>,
        callback: LoadInitialCallback<String, MovieData>
    ) {
        scope.launch {
            try {

                val response = movieService.getTopRatedMovies(language = "en-US", page = 1)

                if (response.isSuccessful) {
                    val lists = response.body()
                    var totalCount = lists!!.total_pages
                    if (totalCount > 0)
                        notificationLiveDataNetworkState.postValue(NetworkResult.Success(true))
                    else
                        notificationLiveDataNetworkState.postValue(
                            NetworkResult.Success(
                                false
                            )
                        )

                    if (totalCount <= 0) totalCount = 1
                    maxPageCount = totalCount / 10 + 1
                    callback.onResult(lists.results, null, 2.toString())
                } else {
                    notificationLiveDataNetworkState.postValue(
                        NetworkResult.Error(
                            "error"
                        )
                    )
                }
            } catch (exception: Exception) {
                if (!exception.message.equals("Job was cancelled")) {
                    notificationLiveDataNetworkState.postValue(
                        NetworkResult.Error(
                            "error"
                        )
                    )

                }
            }
        }

    }

    override fun loadAfter(params: LoadParams<String>, callback: LoadCallback<String, MovieData>) {
        scope.launch {
            try {

                val response = movieService.getTopRatedMovies(language = "en-US", page = params.key.toInt())

                if (response.isSuccessful) {
                    notificationLiveDataNetworkState.postValue(NetworkResult.Success(true))
                    if (params.key.toInt() <= maxPageCount)
                        callback.onResult(
                            response.body()!!.results,
                            (params.key.toInt() + 1).toString()
                        )
                } else {
                    notificationLiveDataNetworkState.postValue(
                        NetworkResult.Error(
                            "Error Occurred"
                        )
                    )
                }
            } catch (exception: Exception) {
                if (!exception.message.equals("Job was cancelled")) {
                    notificationLiveDataNetworkState.postValue(
                        NetworkResult.Error(
                            "Error Occurred"
                        )
                    )
                }
            }
        }
    }

    override fun loadBefore(
        params: LoadParams<String>,
        callback: LoadCallback<String, MovieData>
    ) {
    }


    override fun invalidate() {
        super.invalidate()
        job.cancel()
    }

}