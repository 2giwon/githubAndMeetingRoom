package com.sample.egiwon.githubmeetingroom.data.source

import com.sample.egiwon.githubmeetingroom.data.User
import com.sample.egiwon.githubmeetingroom.data.source.local.GithubLocalDataSource
import com.sample.egiwon.githubmeetingroom.data.source.local.GithubLocalDataSourceImpl
import com.sample.egiwon.githubmeetingroom.data.source.remote.GithubRemoteDataSource
import com.sample.egiwon.githubmeetingroom.data.source.remote.GithubRemoteDataSourceImpl
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.functions.BiFunction

class GithubRepositoryImpl(
    private val githubRemoteDataSource: GithubRemoteDataSource,
    private val githubLocalDataSource: GithubLocalDataSource
) : GithubRepository {

    override fun searchUserInfo(query: String, page: Int): Single<List<User>> =
        githubRemoteDataSource.searchGithubUser(query, page)
            .zipWith(githubLocalDataSource.getLikeUsers(), BiFunction { t1, t2 ->
                t1.users.map { user ->
                    t2.find {
                        user.like = user.id == it.id
                        user.like
                    } ?: user
                }
            })


    override fun setLikeUser(user: User): Completable =
        githubLocalDataSource.setLikeUser(user)

    override fun removeLikeUser(user: User): Completable =
        githubLocalDataSource.removeLikeUser(user)

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