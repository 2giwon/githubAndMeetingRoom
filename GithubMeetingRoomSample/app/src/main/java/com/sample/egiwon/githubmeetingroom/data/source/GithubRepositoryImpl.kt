package com.sample.egiwon.githubmeetingroom.data.source

import com.sample.egiwon.githubmeetingroom.data.User
import com.sample.egiwon.githubmeetingroom.data.UserLikeResponse
import com.sample.egiwon.githubmeetingroom.data.source.local.GithubLocalDataSource
import com.sample.egiwon.githubmeetingroom.data.source.local.GithubLocalDataSourceImpl
import com.sample.egiwon.githubmeetingroom.data.source.remote.GithubRemoteDataSource
import com.sample.egiwon.githubmeetingroom.data.source.remote.GithubRemoteDataSourceImpl
import io.reactivex.Completable
import io.reactivex.Single

class GithubRepositoryImpl(
    private val githubRemoteDataSource: GithubRemoteDataSource,
    private val githubLocalDataSource: GithubLocalDataSource
) : GithubRepository {

    override fun searchUserInfo(query: String, page: Int): Single<UserLikeResponse> =
        githubRemoteDataSource.searchGithubUser(query, page)

    override fun setLikeUser(user: User): Completable =
        githubLocalDataSource.setLikeUser(user)

    override fun getLikeUser(): Single<List<User>> =
        githubLocalDataSource.getLikeUsers()

    companion object {
        private var instance: GithubRepositoryImpl? = null

        fun getInstance(
            githubRemoteDataSource: GithubRemoteDataSourceImpl,
            githubLocalDataSource: GithubLocalDataSourceImpl
        ) = instance ?: GithubRepositoryImpl(githubRemoteDataSource, githubLocalDataSource).apply {
            instance = this
        }
    }
}