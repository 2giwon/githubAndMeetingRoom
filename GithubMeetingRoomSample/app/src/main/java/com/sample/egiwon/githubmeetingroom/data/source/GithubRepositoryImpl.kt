package com.sample.egiwon.githubmeetingroom.data.source

import com.sample.egiwon.githubmeetingroom.data.UserLikeResponse
import com.sample.egiwon.githubmeetingroom.data.source.remote.GithubRemoteDataSource
import com.sample.egiwon.githubmeetingroom.data.source.remote.GithubRemoteDataSourceImpl
import io.reactivex.Single

class GithubRepositoryImpl(
    private val githubRemoteDataSource: GithubRemoteDataSource
) : GithubRepository {

    override fun searchUserInfo(query: String, page: Int): Single<UserLikeResponse> =
        githubRemoteDataSource.searchGithubUser(query, page)

    companion object {
        private var instance: GithubRepositoryImpl? = null

        fun getInstance(parameters: GithubRemoteDataSourceImpl) =
            instance ?: GithubRepositoryImpl(parameters).apply { instance = this }
    }
}