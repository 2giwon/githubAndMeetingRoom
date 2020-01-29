package com.sample.egiwon.githubmeetingroom.github.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sample.egiwon.githubmeetingroom.R
import com.sample.egiwon.githubmeetingroom.base.BaseViewModel
import com.sample.egiwon.githubmeetingroom.data.User
import com.sample.egiwon.githubmeetingroom.data.source.GithubRepository
import io.reactivex.android.schedulers.AndroidSchedulers

class SearchUserViewModel(
    private val githubRepository: GithubRepository
) : BaseViewModel() {

    private val _searchUserResultList = MutableLiveData<List<User>>()
    val searchUserResultList: LiveData<List<User>> get() = _searchUserResultList

    val searchQuery = MutableLiveData<String>()

    private val _isShowLoadingProgressBar = MutableLiveData<Boolean>()
    val isShowLoadingProgressBar: LiveData<Boolean> get() = _isShowLoadingProgressBar

    fun searchUserInfo() {
        if (searchQuery.value.isNullOrEmpty()) {
            mutableErrorTextResId.value = (R.string.error_empty_query)
        } else {
            githubRepository.searchUserInfo(searchQuery.value!!, 1)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    _isShowLoadingProgressBar.value = true
                }
                .doAfterTerminate {
                    _isShowLoadingProgressBar.value = false
                }
                .subscribe({
                    _searchUserResultList.value = it
                }, {
                    mutableErrorTextResId.value = R.string.error_load_fail
                }).addDisposable()
        }
    }

}