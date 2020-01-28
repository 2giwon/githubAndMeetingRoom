package com.sample.egiwon.githubmeetingroom.github.like

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import com.sample.egiwon.githubmeetingroom.R
import com.sample.egiwon.githubmeetingroom.base.BaseRecyclerView
import com.sample.egiwon.githubmeetingroom.data.User
import com.sample.egiwon.githubmeetingroom.databinding.ItemGithubUserBinding
import com.sample.egiwon.githubmeetingroom.github.search.SearchUserViewModel

class UserLikeAdapter(
    private val viewModel: SearchUserViewModel,
    @LayoutRes private val layoutResId: Int = R.layout.item_github_user
) : BaseRecyclerView.Adapter<UserLikeAdapter.UserLikeViewHolder, ItemGithubUserBinding>(
    layoutResId
) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseRecyclerView.BaseViewHolder<ItemGithubUserBinding> = UserLikeViewHolder(parent)

    inner class UserLikeViewHolder(
        parent: ViewGroup
    ) : BaseRecyclerView.BaseViewHolder<ItemGithubUserBinding>(
        parent,
        layoutResId
    ) {
        init {
            binding.vm = viewModel
        }

        override fun bindItem(item: Any?) {
            (item as? User)?.let {
                binding.user = it
            }
        }

    }
}