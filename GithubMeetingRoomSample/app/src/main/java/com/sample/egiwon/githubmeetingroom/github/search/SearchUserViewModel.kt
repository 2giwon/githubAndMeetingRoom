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

    private var currentPage = 1
    private var totalPage = 0
    private var totalCount = 0

    fun searchUsers() {
        if (searchQuery.value.isNullOrEmpty()) {
            mutableErrorTextResId.value = (R.string.error_empty_query)
        } else {
            currentPage = 1
            githubRepository.searchUserInfo(searchQuery.value!!, currentPage)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    _isShowLoadingProgressBar.value = true
                }
                .doAfterTerminate {
                    _isShowLoadingProgressBar.value = false
                }
                .subscribe({
                    _searchUserResultList.value = it.users
                    totalCount = it.totalCount
                }, {
                    mutableErrorTextResId.value = R.string.error_load_fail
                }).addDisposable()
        }
    }

    private fun checkTotalPage(): Boolean {
        totalPage = totalCount / ITEM_PER_PAGE + if (totalCount % ITEM_PER_PAGE > 0) 1 else 0
        return if (currentPage >= totalPage) {
            currentPage = totalPage
            true
        } else false
    }

    fun searchMoreUsers() {
        if (checkTotalPage()) return
        if (isShowLoadingProgressBar.value == true) return

        githubRepository.searchUserInfo(searchQuery.value!!, ++currentPage)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                _isShowLoadingProgressBar.value = true
            }
            .doAfterTerminate {
                _isShowLoadingProgressBar.value = false
            }
            .subscribe({
                _searchUserResultList.value = _searchUserResultList.value?.plus(it.users)
                totalCount = it.totalCount
            }, {
                mutableErrorTextResId.value = R.string.error_load_fail
            }).addDisposable()

    }

    companion object {
        private const val ITEM_PER_PAGE = 30
    }
}