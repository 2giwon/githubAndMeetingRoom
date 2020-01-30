package com.sample.egiwon.githubmeetingroom.data.source.local

import com.sample.egiwon.githubmeetingroom.data.MeetingRoom
import io.reactivex.Single

interface AssetService {
    fun getMeetingRooms(): Single<List<MeetingRoom>>
}