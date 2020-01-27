package com.sample.egiwon.githubmeetingroom.data

import com.google.gson.annotations.SerializedName

data class UserLikeResponse(
    @SerializedName("total_count")
    val totalCount: Int,

    @SerializedName("items")
    val users: List<User>
)
