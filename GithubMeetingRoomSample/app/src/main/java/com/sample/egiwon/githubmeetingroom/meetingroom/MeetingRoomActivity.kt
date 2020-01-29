package com.sample.egiwon.githubmeetingroom.meetingroom

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import com.sample.egiwon.githubmeetingroom.R
import com.sample.egiwon.githubmeetingroom.ext.getCurrentDate
import java.time.LocalDateTime

class MeetingRoomActivity : AppCompatActivity(R.layout.activity_meetingroom) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.run {
            title = LocalDateTime.now().getCurrentDate()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.actionbar_menu, menu)
        return true
    }
}

