package com.sample.egiwon.githubmeetingroom

import android.content.Intent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.sample.egiwon.githubmeetingroom.github.GithubActivity
import com.sample.egiwon.githubmeetingroom.meetingroom.MeetingRoomActivity

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    fun showGithubActivity(view: View) =
            startActivity(Intent(this, GithubActivity::class.java))

    fun showMeetingRoomActivity(view: View) =
            startActivity(Intent(this, MeetingRoomActivity::class.java))

}
