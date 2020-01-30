package com.sample.egiwon.githubmeetingroom.di

import androidx.room.Room
import com.sample.egiwon.githubmeetingroom.data.source.GithubMeetingRoomRepository
import com.sample.egiwon.githubmeetingroom.data.source.GithubMeetingRoomRepositoryImpl
import com.sample.egiwon.githubmeetingroom.data.source.local.GithubMeetingRoomLocalDataSource
import com.sample.egiwon.githubmeetingroom.data.source.local.GithubMeetingRoomLocalDataSourceImpl
import com.sample.egiwon.githubmeetingroom.data.source.local.db.GithubDataBase
import com.sample.egiwon.githubmeetingroom.data.source.remote.GithubRemoteDataSource
import com.sample.egiwon.githubmeetingroom.data.source.remote.GithubRemoteDataSourceImpl
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val dataSourceModule = module {
    single {
        Room.databaseBuilder(
            androidApplication(),
            GithubDataBase::class.java, GithubDataBase.DBNAME
        ).build()
    }

    single { get<GithubDataBase>().githubUserDao() }
    single<GithubMeetingRoomLocalDataSource> {
        GithubMeetingRoomLocalDataSourceImpl(get(), androidApplication())
    }
    single<GithubRemoteDataSource> { GithubRemoteDataSourceImpl(get()) }
    single<GithubMeetingRoomRepository> { GithubMeetingRoomRepositoryImpl(get(), get()) }
}
