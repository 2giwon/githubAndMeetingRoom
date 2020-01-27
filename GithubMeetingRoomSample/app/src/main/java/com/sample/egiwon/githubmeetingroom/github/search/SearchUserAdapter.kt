package com.sample.egiwon.githubmeetingroom.github.search

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import com.sample.egiwon.githubmeetingroom.R
import com.sample.egiwon.githubmeetingroom.base.BaseRecyclerView
import com.sample.egiwon.githubmeetingroom.data.User
import com.sample.egiwon.githubmeetingroom.databinding.ItemGithubUserBinding


class SearchUserAdapter(
    @LayoutRes private val layoutResId: Int = R.layout.item_github_user
) : BaseRecyclerView.Adapter<SearchUserAdapter.SearchUserViewHolder, ItemGithubUserBinding>(
    layoutResId
) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseRecyclerView.BaseViewHolder<ItemGithubUserBinding> = SearchUserViewHolder(parent)

    inner class SearchUserViewHolder(
        parent: ViewGroup
    ) : BaseRecyclerView.BaseViewHolder<ItemGithubUserBinding>(
        parent, layoutResId
    ) {

        override fun bindItem(item: Any?) {
            (item as? User)?.let {
                binding.user = it
            }
        }
    }
}