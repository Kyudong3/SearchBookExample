package com.kyudong3.searchbookexample.di

import com.kyudong3.searchbookexample.SearchBookApp
import com.kyudong3.searchbookexample.di.contributor.ActivityContributor
import com.kyudong3.searchbookexample.di.contributor.FragmentContributor
import com.kyudong3.searchbookexample.di.modules.NetworkModule
import com.kyudong3.searchbookexample.di.modules.RepositoryModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityContributor::class,
        FragmentContributor::class,
        NetworkModule::class,
        RepositoryModule::class
    ]
)
interface AppComponent : AndroidInjector<SearchBookApp>
