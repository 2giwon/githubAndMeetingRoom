package com.sample.egiwon.githubmeetingroom.github

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sample.egiwon.githubmeetingroom.R
import com.sample.egiwon.githubmeetingroom.base.BaseViewModel
import com.sample.egiwon.githubmeetingroom.data.User
import com.sample.egiwon.githubmeetingroom.data.source.GithubMeetingRoomRepository
import io.reactivex.android.schedulers.AndroidSchedulers

class GithubSharedViewModel(
    private val githubMeetingRoomRepository: GithubMeetingRoomRepository
) : BaseViewModel() {

    private val _likeUsers = MutableLiveData<List<User>>()
    val likeUsers: LiveData<List<User>> get() = _likeUsers

    private val _unLikeUser = MutableLiveData<User>()
    val unLikeUser: LiveData<User> get() = _unLikeUser

    private val _removableUser = MutableLiveData<User>()
    val removableUser: LiveData<User> get() = _removableUser

    fun saveOrRemoveChangedLikeUser(user: User) =
        if (user.like) {
            githubMeetingRoomRepository.setLikeUser(user)
        } else {
            _unLikeUser.value = user
            _removableUser.value = user
            githubMeetingRoomRepository.removeLikeUser(user)
        }.observeOn(AndroidSchedulers.mainThread())
            .subscribe()
            .addDisposable()

    fun getLikeUser() = githubMeetingRoomRepository.getLikeUser()
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({
            _likeUsers.value = it
        }, {
            mutableErrorTextResId.value = R.string.error_like_user_load_fail
        }).addDisposable()

}