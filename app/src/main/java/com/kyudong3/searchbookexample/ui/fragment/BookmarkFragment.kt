package com.kyudong3.searchbookexample.ui.fragment

import android.os.Bundle
import android.view.View
import com.kyudong3.searchbookexample.BR
import com.kyudong3.searchbookexample.R
import com.kyudong3.searchbookexample.base.BaseFragment
import com.kyudong3.searchbookexample.databinding.FragmentBookmarkBinding
import com.kyudong3.searchbookexample.ui.widget.recyclerview.listadapter.SearchBookListAdapter
import com.kyudong3.searchbookexample.viewmodels.BookmarkViewModel
import javax.inject.Inject


class BookmarkFragment : BaseFragment<FragmentBookmarkBinding>(R.layout.fragment_bookmark) {

    @Inject
    lateinit var searchBookListAdapter: SearchBookListAdapter

    @Inject
    lateinit var viewModel: BookmarkViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.setVariable(BR.viewModel, viewModel)
        binding.setVariable(BR.listAdapter, searchBookListAdapter)
        binding.executePendingBindings()

//        viewModel.getLocalBookDocuments()
        observe()
    }

    private fun observe() {
        viewModel.bookData.observe(viewLifecycleOwner) {
            searchBookListAdapter.submitList(it)
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.onResume()
    }

    companion object {
        fun newInstance() = BookmarkFragment()
    }
}
