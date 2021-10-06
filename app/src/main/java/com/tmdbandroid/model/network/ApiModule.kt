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

@Module()
class ApiModule {

    @Provides
    fun getGson(): Gson {
        return GsonBuilder().setLenient().create()
    }

    @Provides
    fun getConverterFactory(gson:Gson): GsonConverterFactory {
        return GsonConverterFactory.create(gson)
    }

    @Provides
    fun getRetrofitInstance(okHttpClient: OkHttpClient, gsonConverterFactory: GsonConverterFactory): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(gsonConverterFactory)
            .build()

    }

        @Provides
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


    @Provides
    fun movieDetailApiService(retrofit:Retrofit): MovieService {
        return retrofit.create(MovieService::class.java)
    }
}
