package com.sample.egiwon.githubmeetingroom.github.like

import android.os.Bundle
import android.view.View
import com.sample.egiwon.githubmeetingroom.R
import com.sample.egiwon.githubmeetingroom.base.BaseFragment
import com.sample.egiwon.githubmeetingroom.databinding.FgGithubUserLikeBinding
import com.sample.egiwon.githubmeetingroom.github.GithubSharedViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class UserLikeFragment : BaseFragment<FgGithubUserLikeBinding, GithubSharedViewModel>(
    R.layout.fg_github_user_like
) {
    override val title: String = "LIKE"

    override val viewModel: GithubSharedViewModel by sharedViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bind {
            vm = viewModel
            rvUserLike.adapter = UserLikeAdapter(viewModel)
            rvUserLike.setHasFixedSize(true)
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getLikeUser()
    }
}