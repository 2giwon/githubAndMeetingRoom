package com.sample.egiwon.githubmeetingroom.data.source.local

import com.sample.egiwon.githubmeetingroom.data.MeetingRoom
import io.reactivex.Single

interface MeetingRoomAssetService {
    fun getMeetingRooms(): Single<List<MeetingRoom>>
}