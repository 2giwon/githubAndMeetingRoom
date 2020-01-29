package com.sample.egiwon.githubmeetingroom.data.source

import com.sample.egiwon.githubmeetingroom.data.User
import com.sample.egiwon.githubmeetingroom.data.UserLikeResponse
import com.sample.egiwon.githubmeetingroom.data.source.local.GithubLocalDataSource
import com.sample.egiwon.githubmeetingroom.data.source.remote.GithubRemoteDataSource
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.functions.BiFunction

class GithubRepositoryImpl(
    private val githubRemoteDataSource: GithubRemoteDataSource,
    private val githubLocalDataSource: GithubLocalDataSource
) : GithubRepository {

    override fun searchUserInfo(query: String, page: Int): Single<UserLikeResponse> =
        githubRemoteDataSource.searchGithubUser(query, page)
            .zipWith(githubLocalDataSource.getLikeUsers(), BiFunction { userResponse, likeUsers ->
                UserLikeResponse(
                    userResponse.totalCount,
                    userResponse.users.map { user ->
                        likeUsers.find {
                            user.like = (user.id == it.id)
                            user.like
                        } ?: user
                    }
                )
            })

    override fun setLikeUser(user: User): Completable =
        githubLocalDataSource.setLikeUser(user)

    override fun removeLikeUser(user: User): Completable =
        githubLocalDataSource.removeLikeUser(user)

    override fun getLikeUser(): Single<List<User>> =
        githubLocalDataSource.getLikeUsers()

}