package com.kyudong3.searchbookexample.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.kyudong3.searchbookexample.utils.delegator.DisposableDelegator
import com.kyudong3.searchbookexample.utils.delegator.DisposableDelegatorImpl
import dagger.android.support.DaggerFragment


abstract class BaseFragment<T : ViewDataBinding>(
    @LayoutRes val layoutRes: Int
) : DaggerFragment(),
    DisposableDelegator by DisposableDelegatorImpl() {

    private var _binding: T? = null
    protected val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(
            inflater,
            layoutRes,
            container,
            false
        )
        // FIXME : lifecycleOwner 사용하는 이유
        _binding?.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
