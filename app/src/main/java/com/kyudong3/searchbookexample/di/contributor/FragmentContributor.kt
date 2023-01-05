package com.kyudong3.searchbookexample.di.contributor

import com.kyudong3.searchbookexample.di.scope.FragmentScope
import com.kyudong3.searchbookexample.ui.fragment.BookmarkFragment
import com.kyudong3.searchbookexample.ui.fragment.SearchBookFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class FragmentContributor {

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun provideSearchBookFragment(): SearchBookFragment

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun provideBookmarkFragment(): BookmarkFragment
}