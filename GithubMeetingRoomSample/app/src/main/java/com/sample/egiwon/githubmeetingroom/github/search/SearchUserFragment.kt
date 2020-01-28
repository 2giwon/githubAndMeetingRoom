package com.sample.egiwon.githubmeetingroom.github.search

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sample.egiwon.githubmeetingroom.R
import com.sample.egiwon.githubmeetingroom.base.BaseFragment
import com.sample.egiwon.githubmeetingroom.data.source.GithubRepositoryImpl
import com.sample.egiwon.githubmeetingroom.data.source.local.GithubLocalDataSourceImpl
import com.sample.egiwon.githubmeetingroom.data.source.local.db.GithubDataBase
import com.sample.egiwon.githubmeetingroom.data.source.remote.GithubRemoteDataSourceImpl
import com.sample.egiwon.githubmeetingroom.databinding.FgSearchGithubUserBinding
import com.sample.egiwon.githubmeetingroom.github.GithubSharedViewModel
import com.sample.egiwon.githubmeetingroom.github.SearchUserViewModel

class SearchUserFragment : BaseFragment<FgSearchGithubUserBinding, SearchUserViewModel>(
    R.layout.fg_search_github_user
) {
    override val title: String = "SEARCH"

    @Suppress("UNCHECKED_CAST")
    override val viewModel: SearchUserViewModel by lazy {
        ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T =
                SearchUserViewModel(
                    GithubRepositoryImpl.getInstance(
                        GithubRemoteDataSourceImpl.getInstance(),
                        GithubLocalDataSourceImpl.getInstance(
                            GithubDataBase.getInstance(requireContext().applicationContext)
                                .githubUserDao()
                        )
                    )
                ) as T
        }).get(SearchUserViewModel::class.java)
    }

    @Suppress("UNCHECKED_CAST")
    private val sharedViewModel: GithubSharedViewModel by lazy {
        ViewModelProvider(requireActivity(), object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T =
                GithubSharedViewModel(
                    GithubRepositoryImpl.getInstance(
                        GithubRemoteDataSourceImpl.getInstance(),
                        GithubLocalDataSourceImpl.getInstance(
                            GithubDataBase.getInstance(requireContext().applicationContext)
                                .githubUserDao()
                        )
                    )
                ) as T
        }).get(GithubSharedViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bind {
            vm = viewModel
            sharedVm = sharedViewModel
            rvSearchResultUsers.adapter = SearchUserAdapter(viewModel, sharedViewModel)
            rvSearchResultUsers.setHasFixedSize(true)
        }
    }
}