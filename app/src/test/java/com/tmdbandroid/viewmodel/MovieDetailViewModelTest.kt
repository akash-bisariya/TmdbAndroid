package com.tmdbandroid.viewmodel

import android.content.res.Resources
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.tmdbandroid.TestCoroutineRule
import com.tmdbandroid.model.data.MovieDetail
import com.tmdbandroid.model.network.NetworkResult
import com.tmdbandroid.model.repository.MovieDetailRepository
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.stubbing.Answer

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MovieDetailViewModelTest {
    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var apiUsersObserver: Observer<MovieDetail>

    @Mock
    private lateinit var viewModel: MovieDetailViewModel

    @Before
    fun setUp() {
        // do something if required
    }

    @Mock
    private lateinit var movieDetailRepository: MovieDetailRepository

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

    }

    @Test
    fun verifyLivedataValuesChanges() {
        viewModel = spy(
            MovieDetailViewModel(
                movieDetailRepository, anyString()
            )
        )

        viewModel.movieDetail.observeForever(apiUsersObserver)
        assertNotNull(viewModel.movieDetail)
    }

    @Test
    fun verifySuccessResponse_whenFetch_shouldReturnSuccess() {
        val movieDetail = mock(MovieDetail::class.java)
        testCoroutineRule.runBlockingTest {

            val viewModel = spy(
                MovieDetailViewModel(
                    movieDetailRepository, ""
                )
            )
            viewModel.movieDetail.observeForever(apiUsersObserver)
            `when`(movieDetailRepository.getMovieDetail(anyString())).thenReturn(
                NetworkResult.Success(movieDetail)
            )
            viewModel.getMovieDetail(anyString())
            assertEquals(NetworkResult.Success(movieDetail).data, movieDetail)
        }
    }

    @Test
    fun verifyErrorResponse_whenFetch_shouldReturnFail() {
        val errorMessage = "Error"
        testCoroutineRule.runBlockingTest {

            val viewModel = spy(
                MovieDetailViewModel(
                    movieDetailRepository, anyString()
                )
            )
            viewModel.movieDetail.observeForever(apiUsersObserver)
            `when`(movieDetailRepository.getMovieDetail(anyString())).thenReturn(
                NetworkResult.Error(errorMessage)
            )
            viewModel.getMovieDetail(anyString())
            assertEquals(NetworkResult.Error(errorMessage).errorMessage, errorMessage)
        }
    }


}