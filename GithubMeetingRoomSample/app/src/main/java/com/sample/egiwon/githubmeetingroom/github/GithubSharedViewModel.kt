package com.sample.egiwon.githubmeetingroom.github

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sample.egiwon.githubmeetingroom.R
import com.sample.egiwon.githubmeetingroom.base.BaseViewModel
import com.sample.egiwon.githubmeetingroom.data.User
import com.sample.egiwon.githubmeetingroom.data.source.GithubRepository
import io.reactivex.android.schedulers.AndroidSchedulers

class GithubSharedViewModel(
    private val githubRepository: GithubRepository
) : BaseViewModel() {

    private val _likeUsers = MutableLiveData<List<User>>()
    val likeUsers: LiveData<List<User>> get() = _likeUsers

    private val _removedLikeUser = MutableLiveData<User>()
    val removedLikeUser: LiveData<User> get() = _removedLikeUser

    fun saveAndRemoveChangedLikeUser(user: User) {
        if (user.like) {
            githubRepository.setLikeUser(user)
        } else {
            _removedLikeUser.value = user
            githubRepository.removeLikeUser(user)
        }.observeOn(AndroidSchedulers.mainThread())
            .subscribe()
            .addDisposable()
    }

    fun getLikeUser() = githubRepository.getLikeUser()
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({
            _likeUsers.value = it
        }, {
            mutableErrorTextResId.value = R.string.error_like_user_load_fail
        }).addDisposable()

}