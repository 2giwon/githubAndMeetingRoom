package com.sample.egiwon.githubmeetingroom.meetingroom

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sample.egiwon.githubmeetingroom.R
import com.sample.egiwon.githubmeetingroom.base.BaseViewModel
import com.sample.egiwon.githubmeetingroom.data.MeetingRoom
import com.sample.egiwon.githubmeetingroom.data.source.GithubMeetingRoomRepository
import io.reactivex.android.schedulers.AndroidSchedulers

class MeetingRoomViewModel(
    private val githubMeetingRoomRepository: GithubMeetingRoomRepository
) : BaseViewModel() {

    private val _meetingRooms = MutableLiveData<List<MeetingRoom>>()
    val meetingRooms: LiveData<List<MeetingRoom>> get() = _meetingRooms

    fun getMeetingRooms() =
        githubMeetingRoomRepository.getMeetingRoomFromFile()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _meetingRooms.value = it
            }, {
                mutableErrorTextResId.value = R.string.error_load_json_file
            }).addDisposable()


}