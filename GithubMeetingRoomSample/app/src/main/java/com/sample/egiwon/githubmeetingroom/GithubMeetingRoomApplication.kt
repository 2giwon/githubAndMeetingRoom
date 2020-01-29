package com.sample.egiwon.githubmeetingroom

import android.app.Application
import com.sample.egiwon.githubmeetingroom.di.dataSourceModule
import com.sample.egiwon.githubmeetingroom.di.networkModule
import com.sample.egiwon.githubmeetingroom.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class GithubMeetingRoomApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@GithubMeetingRoomApplication)
            modules(
                listOf(
                    viewModelModule,
                    dataSourceModule,
                    networkModule
                )
            )
        }
    }
}