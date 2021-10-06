package com.tmdbandroid.di

import com.tmdbandroid.model.network.ApiModule
import com.tmdbandroid.view.MovieDetailActivity
import dagger.Component

@Component(modules = [ApiModule::class])
interface ApplicationComponent {

    fun inject(activity: MovieDetailActivity)

}