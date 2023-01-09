package com.kyudong3.searchbookexample.di.contributor

import com.kyudong3.searchbookexample.di.modules.ViewModelFactoryModule
import com.kyudong3.searchbookexample.di.modules.fragment.SearchBookModule
import com.kyudong3.searchbookexample.di.scope.FragmentScope
import com.kyudong3.searchbookexample.ui.fragment.BookmarkFragment
import com.kyudong3.searchbookexample.ui.fragment.SearchBookFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class FragmentContributor {

    @FragmentScope
    @ContributesAndroidInjector(
        modules = [ViewModelFactoryModule::class, SearchBookModule::class]
    )
    abstract fun provideSearchBookFragment(): SearchBookFragment

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun provideBookmarkFragment(): BookmarkFragment
}