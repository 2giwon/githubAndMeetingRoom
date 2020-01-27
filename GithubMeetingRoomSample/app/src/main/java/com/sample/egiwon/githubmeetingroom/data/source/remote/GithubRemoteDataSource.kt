package com.sample.egiwon.githubmeetingroom.data.source.remote

import com.sample.egiwon.githubmeetingroom.data.UserLikeResponse
import io.reactivex.Single

interface GithubRemoteDataSource {
    fun searchGithubUser(query: String, page: Int): Single<UserLikeResponse>
}