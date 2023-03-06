package com.kyudong3.searchbookexample.ui.fragmentimport android.os.Bundleimport android.view.Viewimport androidx.lifecycle.Lifecycleimport androidx.lifecycle.lifecycleScopeimport androidx.lifecycle.repeatOnLifecycleimport com.kyudong3.searchbookexample.BRimport com.kyudong3.searchbookexample.Rimport com.kyudong3.searchbookexample.base.BaseFragmentimport com.kyudong3.searchbookexample.data.dto.BookDocumentimport com.kyudong3.searchbookexample.databinding.FragmentBookmarkBindingimport com.kyudong3.searchbookexample.ui.widget.recyclerview.listadapter.SearchBookListAdapterimport com.kyudong3.searchbookexample.viewmodels.BookmarkViewModelimport kotlinx.coroutines.flow.collectLatestimport kotlinx.coroutines.launchimport javax.inject.Injectclass BookmarkFragment : BaseFragment<FragmentBookmarkBinding>(R.layout.fragment_bookmark) {    @Inject    lateinit var searchBookListAdapter: SearchBookListAdapter    @Inject    lateinit var viewModel: BookmarkViewModel    val bookmarkItemClickListener: ((BookDocument, Int) -> Unit)? by lazy {        { bookDocument, position ->            searchBookListAdapter.notifyItemChanged(position)            viewModel.onClickBookmark(bookDocument)        }    }    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {        super.onViewCreated(view, savedInstanceState)        binding.setVariable(BR.viewModel, viewModel)        binding.setVariable(BR.listAdapter, searchBookListAdapter)        binding.executePendingBindings()        observe()    }    private fun observe() {        lifecycleScope.launch {            repeatOnLifecycle(Lifecycle.State.STARTED) {                viewModel.bookData.collectLatest {                    searchBookListAdapter.submitList(it)                }            }        }    }    companion object {        fun newInstance() = BookmarkFragment()    }}