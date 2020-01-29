package com.sample.egiwon.githubmeetingroom.base

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer

abstract class BaseActivity<B : ViewDataBinding, VM : BaseViewModel>(
    @LayoutRes private val layoutResId: Int
) : AppCompatActivity(layoutResId) {

    protected abstract val viewModel: VM

    protected lateinit var binding: B
        private set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutResId)
        binding.lifecycleOwner = this
        addObserve()
    }

    private fun addObserve() {
        viewModel.errorTextResId.observe(
            this, Observer { showToast(it) }
        )
    }

    protected fun binding(action: B.() -> Unit) {
        binding.run(action)
    }

    private fun showToast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

    private fun showToast(textResId: Int) {
        showToast(getString(textResId))
    }
}