package com.sample.egiwon.githubmeetingroom.github

import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import com.sample.egiwon.githubmeetingroom.R
import com.sample.egiwon.githubmeetingroom.base.BaseActivity
import com.sample.egiwon.githubmeetingroom.databinding.ActivityGithubBinding

class GithubActivity : BaseActivity<ActivityGithubBinding>(R.layout.activity_github) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewPager()
    }

    private fun initViewPager() {
        binding.run {
            vpSearch.run {
                adapter = PagerAdapter(supportFragmentManager)
                addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tlSearch))
            }

            with(tlSearch) {
                listOf(R.string.tab_search, R.string.tab_like).forEach {
                    addTab(newTab().setText(getString(it)))
                }

                addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                    override fun onTabSelected(tab: TabLayout.Tab?) {
                        tab?.run { vpSearch.currentItem = position }
                    }

                    override fun onTabReselected(tab: TabLayout.Tab?) = Unit
                    override fun onTabUnselected(tab: TabLayout.Tab?) = Unit
                })
            }
        }
    }
}