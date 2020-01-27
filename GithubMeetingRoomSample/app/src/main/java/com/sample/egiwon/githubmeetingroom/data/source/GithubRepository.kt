package com.sample.egiwon.githubmeetingroom.data.source

import com.sample.egiwon.githubmeetingroom.data.UserLikeResponse
import io.reactivex.Single

interface GithubRepository {
    fun searchUserInfo(query: String, page: Int): Single<UserLikeResponse>
}