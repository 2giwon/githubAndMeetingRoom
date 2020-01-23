package com.sample.egiwon.githubmeetingroom.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Users")
data class User(

        @ColumnInfo(name = "avatar_url")
        @SerializedName("avatar_url")
        val avatarUrl: String,

        @PrimaryKey
        @ColumnInfo(name = "name")
        @SerializedName("login")
        val name: String,

        @ColumnInfo(name = "score")
        @SerializedName("score")
        val score: Double
)