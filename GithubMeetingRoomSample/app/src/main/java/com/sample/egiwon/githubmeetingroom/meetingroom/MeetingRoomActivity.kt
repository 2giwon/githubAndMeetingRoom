package com.sample.egiwon.githubmeetingroom.meetingroom

import android.os.Bundle
import android.view.Menu
import androidx.lifecycle.Observer
import com.sample.egiwon.githubmeetingroom.R
import com.sample.egiwon.githubmeetingroom.base.BaseActivity
import com.sample.egiwon.githubmeetingroom.databinding.ActivityMeetingroomBinding
import com.sample.egiwon.githubmeetingroom.ext.getCurrentDate
import com.sample.egiwon.githubmeetingroom.meetingroom.customview.getCurrentTime
import org.koin.androidx.viewmodel.ext.android.viewModel

class MeetingRoomActivity : BaseActivity<ActivityMeetingroomBinding, MeetingRoomViewModel>(
    R.layout.activity_meetingroom
) {

    override val viewModel: MeetingRoomViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.run {
            title = getCurrentTime().getCurrentDate()
        }

        binding {
            vm = viewModel
            rvMeetingroomList.adapter = MeetingRoomAdapter()
            rvMeetingroomList.setHasFixedSize(true)

            rvAvailableMeetingroom.adapter = AvailableMeetingRoomAdapter()
            rvAvailableMeetingroom.setHasFixedSize(true)
        }

        viewModel.getMeetingRooms()
        addObserves()
    }

    private fun addObserves() {
        viewModel.reservableMeetingRoomCount.observe(this, Observer { availableCount ->
            binding.tvAvailableMeetingroomCount.text = availableCount.toString()
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.actionbar_menu, menu)
        return true
    }
}

