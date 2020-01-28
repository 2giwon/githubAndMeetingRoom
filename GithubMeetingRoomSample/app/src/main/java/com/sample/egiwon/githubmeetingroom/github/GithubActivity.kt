package com.sample.egiwon.githubmeetingroom.github

import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sample.egiwon.githubmeetingroom.R
import com.sample.egiwon.githubmeetingroom.base.BaseActivity
import com.sample.egiwon.githubmeetingroom.data.source.GithubRepositoryImpl
import com.sample.egiwon.githubmeetingroom.data.source.local.GithubLocalDataSourceImpl
import com.sample.egiwon.githubmeetingroom.data.source.local.db.GithubDataBase
import com.sample.egiwon.githubmeetingroom.data.source.remote.GithubRemoteDataSourceImpl
import com.sample.egiwon.githubmeetingroom.databinding.ActivityGithubBinding
import com.sample.egiwon.githubmeetingroom.ext.setupWithViewPager2

class GithubActivity : BaseActivity<ActivityGithubBinding, GithubSharedViewModel>(
    R.layout.activity_github
) {

    @Suppress("UNCHECKED_CAST")
    override val viewModel: GithubSharedViewModel by lazy {
        ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T =
                GithubSharedViewModel(
                    GithubRepositoryImpl.getInstance(
                        GithubRemoteDataSourceImpl.getInstance(),
                        GithubLocalDataSourceImpl.getInstance(
                            GithubDataBase.getInstance(applicationContext).githubUserDao()
                        )
                    )
                ) as T
        }).get(GithubSharedViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding {
            vm = viewModel
        }
        initViewPager()
    }

    private fun initViewPager() {
        binding {
            val adapter = PagerAdapter(this@GithubActivity)
            vpSearch.adapter = adapter
            tlSearch.setupWithViewPager2(vpSearch, titleProvider = adapter, autoRefresh = true)
        }
    }
}