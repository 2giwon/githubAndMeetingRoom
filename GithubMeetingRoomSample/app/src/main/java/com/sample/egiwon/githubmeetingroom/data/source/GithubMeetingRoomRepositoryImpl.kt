package com.sample.egiwon.githubmeetingroom.data.source

import com.sample.egiwon.githubmeetingroom.data.MeetingRoom
import com.sample.egiwon.githubmeetingroom.data.User
import com.sample.egiwon.githubmeetingroom.data.UserLikeResponse
import com.sample.egiwon.githubmeetingroom.data.source.local.GithubMeetingRoomLocalDataSource
import com.sample.egiwon.githubmeetingroom.data.source.remote.GithubRemoteDataSource
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.functions.BiFunction

class GithubMeetingRoomRepositoryImpl(
    private val githubRemoteDataSource: GithubRemoteDataSource,
    private val githubMeetingRoomLocalDataSource: GithubMeetingRoomLocalDataSource
) : GithubMeetingRoomRepository {

    override fun searchUserInfo(query: String, page: Int): Single<UserLikeResponse> =
        githubRemoteDataSource.searchGithubUser(query, page)
            .zipWith(
                githubMeetingRoomLocalDataSource.getLikeUsers(),
                BiFunction { userResponse, likeUsers ->
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
        githubMeetingRoomLocalDataSource.setLikeUser(user)

    override fun removeLikeUser(user: User): Completable =
        githubMeetingRoomLocalDataSource.removeLikeUser(user)

    override fun getLikeUser(): Single<List<User>> =
        githubMeetingRoomLocalDataSource.getLikeUsers()

    override fun getMeetingRoomFromFile(): Single<List<MeetingRoom>> =
        githubMeetingRoomLocalDataSource.getMeetingRoomFromFile()

}