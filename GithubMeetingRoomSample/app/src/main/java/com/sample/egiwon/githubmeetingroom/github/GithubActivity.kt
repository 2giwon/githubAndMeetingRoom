package com.sample.egiwon.githubmeetingroom.github

import android.os.Bundle
import com.sample.egiwon.githubmeetingroom.R
import com.sample.egiwon.githubmeetingroom.base.BaseActivity
import com.sample.egiwon.githubmeetingroom.databinding.ActivityGithubBinding
import com.sample.egiwon.githubmeetingroom.ext.setupWithViewPager2

class GithubActivity : BaseActivity<ActivityGithubBinding>(R.layout.activity_github) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewPager()
    }

    private fun initViewPager() {
        binding {
            val adapter = PagerAdapter(this@GithubActivity)
            vpSearch.adapter = adapter
            tlSearch.setupWithViewPager2(vpSearch, titleProvider = adapter, autoRefresh = true)
        }
    }
}