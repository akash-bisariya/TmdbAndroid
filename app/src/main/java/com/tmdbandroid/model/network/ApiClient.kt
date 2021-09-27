package com.tmdbandroid.model.network

import com.google.gson.GsonBuilder
import com.tmdbandroid.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {


    private var gson = GsonBuilder().setLenient().create()
    private val okHttpClient = OkHttpClient()
        .newBuilder()
        .connectTimeout(30L, java.util.concurrent.TimeUnit.SECONDS)
        .readTimeout(30L, java.util.concurrent.TimeUnit.SECONDS)
        .writeTimeout(30L, java.util.concurrent.TimeUnit.SECONDS)
//        .addInterceptor(CustomInterceptor())
        .addInterceptor(run {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.apply {
                httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            }
        })
        .build()

    fun getRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

    }
}