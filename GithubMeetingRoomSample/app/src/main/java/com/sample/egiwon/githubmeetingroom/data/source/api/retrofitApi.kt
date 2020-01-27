package com.sample.egiwon.githubmeetingroom.data.source.api

import com.sample.egiwon.githubmeetingroom.BuildConfig
import com.sample.egiwon.githubmeetingroom.data.source.remote.GithubSearchLikeService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object Retrofit {
    val retrofit: GithubSearchLikeService =
        Retrofit.Builder()
            .baseUrl("http://api.github.com/")
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(
                        HttpLoggingInterceptor().apply {
                            level = if (BuildConfig.DEBUG) {
                                HttpLoggingInterceptor.Level.BODY
                            } else {
                                HttpLoggingInterceptor.Level.NONE
                            }
                        }
                    )
                    .build()
            )
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GithubSearchLikeService::class.java)
}