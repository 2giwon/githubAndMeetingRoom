package com.sample.egiwon.githubmeetingroom.data

import com.google.gson.annotations.SerializedName

data class UserLikeResponse(
        @SerializedName("items")
        private val users: List<User>
)
