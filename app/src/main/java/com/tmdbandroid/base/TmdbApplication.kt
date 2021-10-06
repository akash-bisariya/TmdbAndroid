package com.tmdbandroid.base

import android.app.Application
import com.tmdbandroid.di.DaggerApplicationComponent

class TmdbApplication : Application() {

    val appComponent = DaggerApplicationComponent.create()
}