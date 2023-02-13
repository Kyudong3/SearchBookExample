package com.kyudong3.searchbookexample.di.modules.fragment

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kyudong3.searchbookexample.db.repository.BookDocumentRepository
import com.kyudong3.searchbookexample.di.scope.FragmentScope
import com.kyudong3.searchbookexample.di.scope.ViewModelKey
import com.kyudong3.searchbookexample.repository.SearchBookRepository
import com.kyudong3.searchbookexample.ui.fragment.SearchBookFragment
import com.kyudong3.searchbookexample.ui.widget.recyclerview.listadapter.SearchBookListAdapter
import com.kyudong3.searchbookexample.viewmodels.SearchBookViewModel
import com.kyudong3.searchbookexample.viewmodels.ViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap


@Module
object SearchBookModule {

    @Provides
    @FragmentScope
    fun provideFragment(
        fragment: SearchBookFragment
    ): Fragment = fragment

    @Provides
    @FragmentScope
    fun provideViewModelProvider(
        viewModelStoreOwner: SearchBookFragment,
        viewModelFactory: ViewModelFactory
    ) = ViewModelProvider(viewModelStoreOwner, viewModelFactory)

    @Provides
    @FragmentScope
    fun provideViewModelInstance(
        viewModelProvider: ViewModelProvider
    ): SearchBookViewModel = viewModelProvider[SearchBookViewModel::class.java]

    @Provides
    @FragmentScope
    @IntoMap
    @ViewModelKey(SearchBookViewModel::class)
    fun provideSearchBookViewModel(
        searchBookRepository: SearchBookRepository,
        bookDocumentRepository: BookDocumentRepository
    ): ViewModel = SearchBookViewModel(searchBookRepository, bookDocumentRepository)

    @Provides
    @FragmentScope
    fun provideSearchBookListAdapter(
        fragment: SearchBookFragment
    ): SearchBookListAdapter =
        SearchBookListAdapter(itemClickListener = fragment.bookmarkItemClickListener)
}
