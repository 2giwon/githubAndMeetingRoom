package com.sample.egiwon.githubmeetingroom.data.source.local

import com.sample.egiwon.githubmeetingroom.data.MeetingRoom
import com.sample.egiwon.githubmeetingroom.data.User
import com.sample.egiwon.githubmeetingroom.data.source.local.db.GithubUserDao
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class GithubMeetingRoomLocalDataSourceImpl(
    private val githubUserDao: GithubUserDao,
    private val assetService: AssetService
) : GithubMeetingRoomLocalDataSource {

    override fun getLikeUsers(): Single<List<User>> =
        githubUserDao.getLikeUsers()
            .toSingle()
            .subscribeOn(Schedulers.io())

    override fun setLikeUser(user: User): Completable = githubUserDao.setLikeUser(user)
        .subscribeOn(Schedulers.io())

    override fun removeLikeUser(user: User): Completable = githubUserDao.removeLikeUser(user)
        .subscribeOn(Schedulers.io())

    override fun getMeetingRoomFromFile(): Single<List<MeetingRoom>> =
        assetService.getMeetingRooms()
            .subscribeOn(Schedulers.io())


}