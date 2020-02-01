package com.sample.egiwon.githubmeetingroom.meetingroom

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.sample.egiwon.githubmeetingroom.R
import com.sample.egiwon.githubmeetingroom.base.BaseViewModel
import com.sample.egiwon.githubmeetingroom.data.MeetingRoom
import com.sample.egiwon.githubmeetingroom.data.Reservation
import com.sample.egiwon.githubmeetingroom.data.source.GithubMeetingRoomRepository
import com.sample.egiwon.githubmeetingroom.ext.convertTimeToReserveTime
import io.reactivex.android.schedulers.AndroidSchedulers
import java.time.LocalDateTime

class MeetingRoomViewModel(
    private val githubMeetingRoomRepository: GithubMeetingRoomRepository
) : BaseViewModel() {

    private val _meetingRooms = MutableLiveData<List<MeetingRoom>>()
    val meetingRooms: LiveData<List<MeetingRoom>> get() = _meetingRooms

    private var availableMeetingRoomCount = 0

    val availableMeetingRooms: LiveData<List<MeetingRoom>> = Transformations.map(meetingRooms) {
        getAvailableMeetingRoom(it)
    }

    private val _isShowAvailableMeetingRooms = MutableLiveData<Boolean>()
    val isShowAvailableMeetingRooms: LiveData<Boolean> get() = _isShowAvailableMeetingRooms

    private val _reservableMeetingRoomCount = MutableLiveData<Int>()
    val reservableMeetingRoomCount: LiveData<Int> get() = _reservableMeetingRoomCount

    fun getMeetingRooms() =
        githubMeetingRoomRepository.getMeetingRoomFromFile()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _meetingRooms.value = it
            }, {
                mutableErrorTextResId.value = R.string.error_load_json_file
            }).addDisposable()


    private fun getAvailableMeetingRoom(meetingRooms: List<MeetingRoom>): List<MeetingRoom> {
        val availableMeetingRooms = meetingRooms.toMutableList()
        meetingRooms.forEach { meetingRoom ->
            var deadLine = DEAD_LINE
            val currentTime = LocalDateTime.now().convertTimeToReserveTime().toInt()
            deadLine -= currentTime

            meetingRoom.reservations.forEach {
                if (deadLine > 0) {

                    when {
                        it.endTime.toInt() > currentTime && it.startTime.toInt() > currentTime -> {
                            deadLine -= calculatePeriod(it)
                        }
                        it.endTime.toInt() > currentTime -> {
                            deadLine -= (it.endTime.toInt() - currentTime)
                        }
                    }

                }
            }

            if (deadLine > 0) {
                availableMeetingRoomCount++
            } else {
                availableMeetingRooms.remove(meetingRoom)
            }
        }

        _reservableMeetingRoomCount.value = availableMeetingRoomCount
        _isShowAvailableMeetingRooms.value = availableMeetingRoomCount != 0
        return availableMeetingRooms
    }

    private fun calculatePeriod(reservation: Reservation): Int {
        var period = (reservation.endTime.toInt() - reservation.startTime.toInt())
        if (period % 100 > 50) period -= 20
        else if (period % 100 > 0) period += 20

        return period
    }

    companion object {
        private const val DEAD_LINE = 1800
    }

}