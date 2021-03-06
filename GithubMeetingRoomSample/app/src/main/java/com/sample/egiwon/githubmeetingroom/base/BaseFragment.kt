package com.sample.egiwon.githubmeetingroom.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer

abstract class BaseFragment<B : ViewDataBinding, VM : BaseViewModel>(
        @LayoutRes private val layoutResId: Int
) : Fragment() {

    protected abstract val viewModel: VM

    protected lateinit var binding: B
        private set

    abstract val title: String

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutResId, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        addObserve()
        return binding.root
    }

    private fun addObserve() {
        viewModel.errorTextResId.observe(
            viewLifecycleOwner, Observer { showToast(it) }
        )
    }

    protected fun bind(action: B.() -> Unit) {
        binding.run(action)
    }

    private fun showToast(text: String) {
        Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT).show()
    }

    private fun showToast(textResId: Int) {
        showToast(getString(textResId))
    }

}