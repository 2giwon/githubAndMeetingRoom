package com.sample.egiwon.githubmeetingroom.ext

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sample.egiwon.githubmeetingroom.base.BaseRecyclerView
import com.sample.egiwon.githubmeetingroom.data.User
import com.sample.egiwon.githubmeetingroom.github.search.SearchUserAdapter

@Suppress("UNCHECKED_CAST")
@BindingAdapter("replaceItems")
fun RecyclerView.replaceItems(items: List<Any>?) {
    (this.adapter as? BaseRecyclerView.Adapter<Any, *>)?.run {
        replaceAll(items)
        notifyDataSetChanged()
    }
}

@Suppress("UNCHECKED_CAST")
@BindingAdapter("replaceCheckLikeUsers")
fun RecyclerView.replaceCheckLikeUsers(items: List<Any>?) {
    (this.adapter as? SearchUserAdapter)?.run {
        items?.let {
            bindLikeUser(it as List<User>)
            notifyDataSetChanged()
        }
    }
}