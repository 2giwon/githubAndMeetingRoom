package com.sample.egiwon.githubmeetingroom.github.search

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import com.sample.egiwon.githubmeetingroom.R
import com.sample.egiwon.githubmeetingroom.base.BaseRecyclerView
import com.sample.egiwon.githubmeetingroom.data.User
import com.sample.egiwon.githubmeetingroom.databinding.ItemGithubUserBinding
import com.sample.egiwon.githubmeetingroom.github.GithubSharedViewModel

class SearchUserAdapter(
    private val sharedViewModel: GithubSharedViewModel,
    @LayoutRes private val layoutResId: Int = R.layout.item_github_user
) : BaseRecyclerView.Adapter<User, ItemGithubUserBinding>(
    layoutResId
) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseRecyclerView.BaseViewHolder<ItemGithubUserBinding> = SearchUserViewHolder(parent)

    val onUnLikeUser: (User) -> Unit = {
        (0..itemCount).forEach { index ->
            if (getItem(index)?.id == it.id) {
                getItem(index)?.like = getItem(index)?.id != it.id
            }
        }
        notifyDataSetChanged()
    }

    inner class SearchUserViewHolder(
        parent: ViewGroup
    ) : BaseRecyclerView.BaseViewHolder<ItemGithubUserBinding>(
        parent,
        layoutResId
    ) {

        init {
            binding.sharedVm = sharedViewModel
        }

        override fun bindItem(item: Any?) {
            (item as? User)?.let {
                binding.user = it
            }
        }
    }
}