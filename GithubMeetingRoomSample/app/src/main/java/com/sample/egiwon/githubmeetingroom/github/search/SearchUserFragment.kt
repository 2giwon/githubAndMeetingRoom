package com.sample.egiwon.githubmeetingroom.github.search

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.sample.egiwon.githubmeetingroom.R
import com.sample.egiwon.githubmeetingroom.base.BaseFragment
import com.sample.egiwon.githubmeetingroom.databinding.FgSearchGithubUserBinding
import com.sample.egiwon.githubmeetingroom.github.GithubSharedViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchUserFragment : BaseFragment<FgSearchGithubUserBinding, SearchUserViewModel>(
    R.layout.fg_search_github_user
) {
    override val title: String = "SEARCH"

    override val viewModel: SearchUserViewModel by viewModel()

    private val sharedViewModel: GithubSharedViewModel by sharedViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bind {
            vm = viewModel
            sharedVm = sharedViewModel
            rvSearchResultUsers.adapter = SearchUserAdapter(sharedViewModel)
            rvSearchResultUsers.setHasFixedSize(true)
        }

        addObserve()
    }

    private fun addObserve() {
        sharedViewModel.removedLikeUser.observe(viewLifecycleOwner, Observer { user ->
            (binding.rvSearchResultUsers.adapter as? SearchUserAdapter)?.let {
                it.onUnLikeUser(user)
            }
        })
    }
}