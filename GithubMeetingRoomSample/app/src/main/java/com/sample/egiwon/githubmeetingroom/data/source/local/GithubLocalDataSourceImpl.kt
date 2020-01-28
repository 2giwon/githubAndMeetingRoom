package com.sample.egiwon.githubmeetingroom.data.source.local

import com.sample.egiwon.githubmeetingroom.data.User
import com.sample.egiwon.githubmeetingroom.data.source.local.db.GithubUserDao
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class GithubLocalDataSourceImpl(
    private val githubUserDao: GithubUserDao
) : GithubLocalDataSource {

    override fun getLikeUsers(): Single<List<User>> =
        githubUserDao.getLikeUsers()
            .toSingle()
            .subscribeOn(Schedulers.io())

    override fun setLikeUser(user: User): Completable = githubUserDao.setLikeUser(user)
        .subscribeOn(Schedulers.io())

    override fun removeLikeUser(user: User): Completable = githubUserDao.removeLikeUser(user)
        .subscribeOn(Schedulers.io())

    companion object {
        private var instance: GithubLocalDataSourceImpl? = null

        fun getInstance(githubDao: GithubUserDao): GithubLocalDataSourceImpl =
            instance ?: GithubLocalDataSourceImpl(githubDao).apply { instance = this }
    }


}