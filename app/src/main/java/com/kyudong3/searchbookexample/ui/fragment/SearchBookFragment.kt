package com.kyudong3.searchbookexample.ui.fragment

import android.os.Bundle
import android.view.View
import com.kyudong3.searchbookexample.R
import com.kyudong3.searchbookexample.base.BaseFragment
import com.kyudong3.searchbookexample.databinding.FragmentSearchBookBinding


class SearchBookFragment : BaseFragment<FragmentSearchBookBinding>(R.layout.fragment_search_book) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.executePendingBindings()
    }

    companion object {
        fun newInstance() = SearchBookFragment()
    }
}
