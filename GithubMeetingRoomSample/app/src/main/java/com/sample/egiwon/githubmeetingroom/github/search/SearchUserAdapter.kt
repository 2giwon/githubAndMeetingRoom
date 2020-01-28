package com.sample.egiwon.githubmeetingroom.github.search

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import com.sample.egiwon.githubmeetingroom.R
import com.sample.egiwon.githubmeetingroom.base.BaseRecyclerView
import com.sample.egiwon.githubmeetingroom.data.User
import com.sample.egiwon.githubmeetingroom.databinding.ItemGithubUserBinding
import com.sample.egiwon.githubmeetingroom.github.GithubSharedViewModel
import com.sample.egiwon.githubmeetingroom.github.SearchUserViewModel

class SearchUserAdapter(
    private val viewModel: SearchUserViewModel,
    private val sharedViewModel: GithubSharedViewModel,
    @LayoutRes private val layoutResId: Int = R.layout.item_github_user
) : BaseRecyclerView.Adapter<User, ItemGithubUserBinding>(
    layoutResId
) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseRecyclerView.BaseViewHolder<ItemGithubUserBinding> = SearchUserViewHolder(parent)

    fun bindLikeUser(items: List<User>) {
        (0..itemCount).map { index ->
            getItem(index)?.let { adapterUser ->
                adapterUser.like = items.find {
                    adapterUser.like = (it.id == adapterUser.id)
                    adapterUser.like
                }?.like ?: false
            }

        }
    }

    inner class SearchUserViewHolder(
        parent: ViewGroup
    ) : BaseRecyclerView.BaseViewHolder<ItemGithubUserBinding>(
        parent,
        layoutResId
    ) {

        init {
            binding.vm = viewModel
            binding.sharedVm = sharedViewModel
        }

        override fun bindItem(item: Any?) {
            (item as? User)?.let {
                binding.user = it
            }
        }
    }
}