package com.sample.egiwon.githubmeetingroom.data.source.local

import com.sample.egiwon.githubmeetingroom.data.User
import io.reactivex.Completable
import io.reactivex.Single

interface GithubLocalDataSource {
    fun getLikeUsers(): Single<List<User>>

    fun setLikeUser(user: User): Completable

    fun removeLikeUser(user: User): Completable
}