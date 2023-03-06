package com.kyudong3.searchbookexample.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.kyudong3.searchbookexample.BR
import com.kyudong3.searchbookexample.R
import com.kyudong3.searchbookexample.base.BaseFragment
import com.kyudong3.searchbookexample.data.dto.BookDocument
import com.kyudong3.searchbookexample.databinding.FragmentSearchBookBinding
import com.kyudong3.searchbookexample.ui.widget.dialog.RxAlertDialog
import com.kyudong3.searchbookexample.ui.widget.recyclerview.listadapter.SearchBookListAdapter
import com.kyudong3.searchbookexample.viewmodels.SearchBookViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


class SearchBookFragment : BaseFragment<FragmentSearchBookBinding>(R.layout.fragment_search_book) {

    @Inject
    lateinit var viewModel: SearchBookViewModel

    @Inject
    lateinit var searchBookListAdapter: SearchBookListAdapter

    val bookmarkItemClickListener: ((BookDocument, Int) -> Unit)? by lazy {
        { document, _ ->
            viewModel.onClickBookmark(document)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.setVariable(BR.viewModel, viewModel)
        binding.setVariable(BR.listAdapter, searchBookListAdapter)
        binding.executePendingBindings()

        observe()
    }

    private fun observe() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.bookData.collectLatest {
                    if (it.isEmpty()) {
                        searchBookListAdapter.submitList(null)
                        showAlertDialog()
                    } else {
                        searchBookListAdapter.submitList(it)
                    }
                }
            }
        }
    }

    private fun showAlertDialog() {
        RxAlertDialog
            .from(requireContext())
            .setCancelable(false)
            .setMessage(R.string.empty_search_data)
            .forObservable(R.string.confirm, R.string.close)
            .baseSubscribe { }
    }

    companion object {
        fun newInstance() = SearchBookFragment()
    }
}
