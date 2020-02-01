package com.sample.egiwon.githubmeetingroom.data.source.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sample.egiwon.githubmeetingroom.data.User

@Database(
    entities = [User::class],
    version = 1,
    exportSchema = false
)
abstract class GithubDataBase : RoomDatabase() {
    abstract fun githubUserDao(): GithubUserDao

    companion object {
        const val DB_NAME = "Users.db"
    }
}