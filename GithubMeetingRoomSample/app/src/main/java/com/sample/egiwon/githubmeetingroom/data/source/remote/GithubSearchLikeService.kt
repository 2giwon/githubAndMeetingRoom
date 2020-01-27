package com.sample.egiwon.githubmeetingroom.data.source.remote

import com.sample.egiwon.githubmeetingroom.data.UserLikeResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubSearchLikeService {
    @GET("/search/users")
    fun getUserInfo(
        @Query("q")
        userID: String,
        @Query("page")
        page: Int = 1
    ): Single<UserLikeResponse>
}