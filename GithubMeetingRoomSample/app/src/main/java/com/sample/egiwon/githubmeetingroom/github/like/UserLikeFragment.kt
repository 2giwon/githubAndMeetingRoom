package com.sample.egiwon.githubmeetingroom.github.like

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sample.egiwon.githubmeetingroom.R
import com.sample.egiwon.githubmeetingroom.base.BaseFragment
import com.sample.egiwon.githubmeetingroom.databinding.FgGithubUserLikeBinding

class UserLikeFragment : BaseFragment<FgGithubUserLikeBinding, UserLikeViewModel>(
    R.layout.fg_github_user_like
) {
    @Suppress("UNCHECKED_CAST")
    override val viewModel: UserLikeViewModel by lazy {
        ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T =
                UserLikeViewModel() as T
        }).get(UserLikeViewModel::class.java)
    }

}