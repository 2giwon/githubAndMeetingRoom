package com.sample.egiwon.githubmeetingroom.di

import com.sample.egiwon.githubmeetingroom.github.GithubSharedViewModel
import com.sample.egiwon.githubmeetingroom.github.search.SearchUserViewModel
import com.sample.egiwon.githubmeetingroom.meetingroom.MeetingRoomViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel { GithubSharedViewModel(get()) }

    viewModel { SearchUserViewModel(get()) }

    viewModel { MeetingRoomViewModel(get()) }
}