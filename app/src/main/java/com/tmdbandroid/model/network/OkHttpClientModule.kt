//package com.tmdbandroid.model.network
//
//import dagger.Module
//import dagger.Provides
//import okhttp3.OkHttpClient
//import okhttp3.logging.HttpLoggingInterceptor
//
//@Module
//class OkHttpClientModule {
//
//    @Provides
//    fun getOkHttpClient(): OkHttpClient {
//        return OkHttpClient()
//            .newBuilder()
//            .connectTimeout(30L, java.util.concurrent.TimeUnit.SECONDS)
//            .readTimeout(30L, java.util.concurrent.TimeUnit.SECONDS)
//            .writeTimeout(30L, java.util.concurrent.TimeUnit.SECONDS)
//            .addInterceptor(run {
//                val httpLoggingInterceptor = HttpLoggingInterceptor()
//                httpLoggingInterceptor.apply {
//                    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
//                }
//            })
//            .build()
//    }
//
//}