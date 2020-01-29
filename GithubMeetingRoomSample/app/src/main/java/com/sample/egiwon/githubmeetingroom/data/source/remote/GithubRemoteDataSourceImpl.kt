package com.sample.egiwon.githubmeetingroom.data.source.remote

import com.sample.egiwon.githubmeetingroom.data.UserLikeResponse
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class GithubRemoteDataSourceImpl(
    private val service: GithubSearchLikeService
) : GithubRemoteDataSource {
    override fun searchGithubUser(query: String, page: Int): Single<UserLikeResponse> =
        service.getUserInfo(query, page)
            .subscribeOn(Schedulers.io())

}