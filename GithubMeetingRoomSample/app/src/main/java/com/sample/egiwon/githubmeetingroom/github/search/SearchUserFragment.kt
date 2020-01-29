package com.sample.egiwon.githubmeetingroom.github.search

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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

        setScrollListener()
        addObserve()
    }

    private fun addObserve() {
        sharedViewModel.removedLikeUser.observe(viewLifecycleOwner, Observer { user ->
            (binding.rvSearchResultUsers.adapter as? SearchUserAdapter)?.let {
                it.onUnLikeUser(user)
            }
        })
    }

    private fun setScrollListener() {
        with(binding.rvSearchResultUsers) {
            val layoutManager = layoutManager as LinearLayoutManager
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    if (dy > 0) {
                        val lastVisibleItemPos =
                            layoutManager.findLastCompletelyVisibleItemPosition()

                        if (lastVisibleItemPos + 1 == adapter?.itemCount) {
                            viewModel.searchMoreUsers()
                        }
                    }
                }
            })
        }

    }
}