package com.sample.egiwon.githubmeetingroom.data.source

import com.sample.egiwon.githubmeetingroom.data.User
import com.sample.egiwon.githubmeetingroom.data.UserLikeResponse
import io.reactivex.Completable
import io.reactivex.Single

interface GithubRepository {
    fun searchUserInfo(query: String, page: Int): Single<UserLikeResponse>

    fun setLikeUser(user: User): Completable

    fun getLikeUser(): Single<List<User>>
}