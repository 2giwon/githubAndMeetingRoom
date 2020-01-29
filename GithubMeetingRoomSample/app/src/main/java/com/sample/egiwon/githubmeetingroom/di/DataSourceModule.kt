package com.sample.egiwon.githubmeetingroom.di

import androidx.room.Room
import com.sample.egiwon.githubmeetingroom.data.source.GithubRepository
import com.sample.egiwon.githubmeetingroom.data.source.GithubRepositoryImpl
import com.sample.egiwon.githubmeetingroom.data.source.local.GithubLocalDataSource
import com.sample.egiwon.githubmeetingroom.data.source.local.GithubLocalDataSourceImpl
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
    single<GithubLocalDataSource> { GithubLocalDataSourceImpl(get()) }
    single<GithubRemoteDataSource> { GithubRemoteDataSourceImpl(get()) }
    single<GithubRepository> { GithubRepositoryImpl(get(), get()) }
}
