package com.kyudong3.searchbookexample.ui.fragment

import android.os.Bundle
import android.view.View
import com.kyudong3.searchbookexample.BR
import com.kyudong3.searchbookexample.R
import com.kyudong3.searchbookexample.base.BaseFragment
import com.kyudong3.searchbookexample.databinding.FragmentSearchBookBinding
import com.kyudong3.searchbookexample.ui.widget.recyclerview.SearchBookListAdapter
import com.kyudong3.searchbookexample.viewmodels.SearchBookViewModel
import javax.inject.Inject


class SearchBookFragment : BaseFragment<FragmentSearchBookBinding>(R.layout.fragment_search_book) {

    @Inject
    lateinit var viewModel: SearchBookViewModel

    @Inject
    lateinit var searchBookListAdapter: SearchBookListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.setVariable(BR.viewModel, viewModel)
        binding.setVariable(BR.listAdapter, searchBookListAdapter)
        binding.executePendingBindings()

        observe()
    }

    private fun observe() {
        viewModel.bookData.observe(viewLifecycleOwner) {
            searchBookListAdapter.submitList(it)
        }

        searchBookListAdapter.setItemClickListener { view, bookDocument ->
        }
    }

    companion object {
        fun newInstance() = SearchBookFragment()
    }
}
