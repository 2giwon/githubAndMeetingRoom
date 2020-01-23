package com.sample.egiwon.githubmeetingroom.github.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sample.egiwon.githubmeetingroom.R
import com.sample.egiwon.githubmeetingroom.base.BaseFragment
import com.sample.egiwon.githubmeetingroom.databinding.FgSearchGithubUserBinding

class SearchUserFragment : BaseFragment<FgSearchGithubUserBinding, SearchUserViewModel>(
        R.layout.fg_search_github_user
) {
    @Suppress("UNCHECKED_CAST")
    override val viewModel: SearchUserViewModel by lazy {
        ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T =
                SearchUserViewModel() as T
        }).get(SearchUserViewModel::class.java)
    }

}