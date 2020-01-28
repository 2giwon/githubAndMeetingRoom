package com.sample.egiwon.githubmeetingroom.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "users")
data class User(

    @PrimaryKey
    @field:SerializedName("id")
    val id: String,

    @field:SerializedName("avatar_url")
    val avatarUrl: String,

    @field:SerializedName("login")
    val name: String,

    @field:SerializedName("score")
    val score: Double,

    @field:SerializedName("like")
    val like: Boolean = false
)