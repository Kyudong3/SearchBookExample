package com.kyudong3.searchbookexample.di.modules.fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kyudong3.searchbookexample.db.repository.BookDocumentRepository
import com.kyudong3.searchbookexample.di.scope.FragmentScope
import com.kyudong3.searchbookexample.di.scope.ViewModelKey
import com.kyudong3.searchbookexample.ui.fragment.BookmarkFragment
import com.kyudong3.searchbookexample.ui.widget.recyclerview.listadapter.SearchBookListAdapter
import com.kyudong3.searchbookexample.viewmodels.BookmarkViewModel
import com.kyudong3.searchbookexample.viewmodels.ViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap


@Module
object BookmarkModule {

    @Provides
    @FragmentScope
    fun provideViewModelProvider(
        viewModelStoreOwner: BookmarkFragment,
        viewModelFactory: ViewModelFactory
    ) = ViewModelProvider(viewModelStoreOwner, viewModelFactory)

    @Provides
    @FragmentScope
    fun provideViewModelInstance(
        viewModelProvider: ViewModelProvider
    ): BookmarkViewModel = viewModelProvider[BookmarkViewModel::class.java]

    @Provides
    @FragmentScope
    @IntoMap
    @ViewModelKey(BookmarkViewModel::class)
    fun provideBookmarkViewModel(
        bookDocumentRepository: BookDocumentRepository
    ): ViewModel = BookmarkViewModel(bookDocumentRepository)

    @Provides
    @FragmentScope
    fun provideSearchBookListAdapter(): SearchBookListAdapter = SearchBookListAdapter()
}