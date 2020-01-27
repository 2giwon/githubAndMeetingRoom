package com.sample.egiwon.githubmeetingroom.data.source.remote

import com.sample.egiwon.githubmeetingroom.data.UserLikeResponse
import com.sample.egiwon.githubmeetingroom.data.source.api.Retrofit
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class GithubRemoteDataSourceImpl : GithubRemoteDataSource {
    override fun searchGithubUser(query: String, page: Int): Single<UserLikeResponse> =
        Retrofit.retrofit.getUserInfo(query, page)
            .subscribeOn(Schedulers.io())

    companion object {
        private var instance: GithubRemoteDataSourceImpl? = null

        fun getInstance() = instance ?: GithubRemoteDataSourceImpl().apply { instance = this }
    }
}