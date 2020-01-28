package com.sample.egiwon.githubmeetingroom.data.source.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
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
        private var INSTANCE: GithubDataBase? = null

        private val lock = Any()

        fun getInstance(context: Context): GithubDataBase {
            synchronized(lock) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        GithubDataBase::class.java, "Users.db"
                    ).build()
                }
                return INSTANCE!!
            }
        }
    }
}