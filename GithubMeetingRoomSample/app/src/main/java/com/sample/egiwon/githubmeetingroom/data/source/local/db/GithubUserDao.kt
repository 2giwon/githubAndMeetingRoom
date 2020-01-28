package com.sample.egiwon.githubmeetingroom.data.source.local.db

import androidx.room.*
import com.sample.egiwon.githubmeetingroom.data.User
import io.reactivex.Completable
import io.reactivex.Maybe

@Dao
interface GithubUserDao {

    @Query("SELECT * FROM users")
    fun getLikeUsers(): Maybe<List<User>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setLikeUser(user: User): Completable

    @Delete
    fun removeLikeUser(user: User): Completable
}