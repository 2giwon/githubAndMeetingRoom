package com.sample.egiwon.githubmeetingroom.meetingroom

import android.os.Bundle
import android.view.Menu
import androidx.lifecycle.Observer
import com.sample.egiwon.githubmeetingroom.R
import com.sample.egiwon.githubmeetingroom.base.BaseActivity
import com.sample.egiwon.githubmeetingroom.databinding.ActivityMeetingroomBinding
import com.sample.egiwon.githubmeetingroom.ext.getCurrentDate
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.time.LocalDateTime

class MeetingRoomActivity : BaseActivity<ActivityMeetingroomBinding, MeetingRoomViewModel>(
    R.layout.activity_meetingroom
) {

    override val viewModel: MeetingRoomViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.run {
            title = LocalDateTime.now().getCurrentDate()
        }

        binding {
            rvMeetingroomList.adapter = MeetingRoomAdapter()
            rvMeetingroomList.setHasFixedSize(true)
        }
        viewModel.getMeetingRooms()

        setObserve()
    }

    private fun setObserve() {
        viewModel.meetingRooms.observe(this, Observer {
            (binding.rvMeetingroomList.adapter as? MeetingRoomAdapter)?.replaceAll(it)
        })

        viewModel.reservableMeetingRoomCount.observe(this, Observer {
            binding.tvAvailableMeetingroomCount.text = it.toString()
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.actionbar_menu, menu)
        return true
    }
}

