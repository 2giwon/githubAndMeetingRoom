package com.sample.egiwon.githubmeetingroom.github.like

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import com.sample.egiwon.githubmeetingroom.R
import com.sample.egiwon.githubmeetingroom.base.BaseRecyclerView
import com.sample.egiwon.githubmeetingroom.data.User
import com.sample.egiwon.githubmeetingroom.databinding.ItemGithubUserBinding
import com.sample.egiwon.githubmeetingroom.github.GithubSharedViewModel

class UserLikeAdapter(
    private val viewModel: GithubSharedViewModel,
    @LayoutRes private val layoutResId: Int = R.layout.item_github_user
) : BaseRecyclerView.Adapter<User, ItemGithubUserBinding>(
    layoutResId
) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseRecyclerView.BaseViewHolder<ItemGithubUserBinding> = UserLikeViewHolder(parent)

    val onRemoveUnlikeUser: (User) -> Unit = {
        (0..itemCount).forEach { index ->
            if (getItem(index)?.id == it.id) {
                removeItem(index)
            }
        }
        notifyDataSetChanged()
    }

    inner class UserLikeViewHolder(
        parent: ViewGroup
    ) : BaseRecyclerView.BaseViewHolder<ItemGithubUserBinding>(
        parent,
        layoutResId
    ) {
        init {
            binding.sharedVm = viewModel
        }

        override fun bindItem(item: Any?) {
            (item as? User)?.let {
                binding.user = it
            }
        }

    }
}