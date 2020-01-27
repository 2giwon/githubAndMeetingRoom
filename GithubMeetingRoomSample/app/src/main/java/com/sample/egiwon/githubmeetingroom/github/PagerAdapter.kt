package com.sample.egiwon.githubmeetingroom.github

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.sample.egiwon.githubmeetingroom.ext.TabTitleProvider
import com.sample.egiwon.githubmeetingroom.github.like.UserLikeFragment
import com.sample.egiwon.githubmeetingroom.github.search.SearchUserFragment

class PagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity), TabTitleProvider {

    private val fragments = listOf(SearchUserFragment(), UserLikeFragment())

    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment = fragments[position]

    override fun getItemTitle(position: Int): CharSequence = fragments[position].title
}