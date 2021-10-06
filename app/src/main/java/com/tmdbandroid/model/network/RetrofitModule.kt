package com.tmdbandroid.model.network

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.tmdbandroid.BuildConfig
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object  RetrofitModuleListing {

    fun getGson(): Gson {
        return GsonBuilder().setLenient().create()
    }


    fun getConverterFactory():GsonConverterFactory{
        return GsonConverterFactory.create(getGson())
    }

    fun getOkHttpClient(): OkHttpClient {
        return OkHttpClient()
            .newBuilder()
            .connectTimeout(30L, java.util.concurrent.TimeUnit.SECONDS)
            .readTimeout(30L, java.util.concurrent.TimeUnit.SECONDS)
            .writeTimeout(30L, java.util.concurrent.TimeUnit.SECONDS)
            .addInterceptor(run {
                val httpLoggingInterceptor = HttpLoggingInterceptor()
                httpLoggingInterceptor.apply {
                    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
                }
            })
            .build()
    }
//    private var gson = GsonBuilder().setLenient().create()


    fun getRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .client(getOkHttpClient())
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(getConverterFactory())
            .build()

    }
}