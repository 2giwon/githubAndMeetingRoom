package com.sample.egiwon.githubmeetingroom.github

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sample.egiwon.githubmeetingroom.base.BaseViewModel
import com.sample.egiwon.githubmeetingroom.data.User
import com.sample.egiwon.githubmeetingroom.data.source.GithubRepository
import io.reactivex.android.schedulers.AndroidSchedulers

class GithubSharedViewModel(
    private val githubRepository: GithubRepository
) : BaseViewModel() {

    private val _likeUsers = MutableLiveData<ArrayList<User>>()
    val likeUsers: LiveData<ArrayList<User>> get() = _likeUsers

    fun setLikeUser(user: User) {
        user.like = !user.like
        if (user.like) {
            _likeUsers.value?.add(user)
            githubRepository.setLikeUser(user)
        } else {
            _likeUsers.value?.remove(user)
            githubRepository.removeLikeUser(user)
        }.observeOn(AndroidSchedulers.mainThread())
            .subscribe()
            .addDisposable()
    }
}