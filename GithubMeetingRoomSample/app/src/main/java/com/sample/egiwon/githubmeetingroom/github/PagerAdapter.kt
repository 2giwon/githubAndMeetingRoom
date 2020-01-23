package com.sample.egiwon.githubmeetingroom.github

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class PagerAdapter(fm: FragmentManager) :
        FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment =
            ContentFragment.newInstance(Tab.values()[position])

    override fun getCount(): Int = Tab.values().size

}