package com.sample.egiwon.githubmeetingroom.github

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.sample.egiwon.githubmeetingroom.github.like.UserLikeFragment
import com.sample.egiwon.githubmeetingroom.github.search.SearchUserFragment

class PagerAdapter(fm: FragmentManager) :
        FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val fragments = listOf(SearchUserFragment(), UserLikeFragment())

    override fun getItem(position: Int): Fragment = fragments[position]

    override fun getCount(): Int = fragments.size
}