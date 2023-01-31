package com.kyudong3.searchbookexample.di.modules

import com.kyudong3.searchbookexample.SearchBookApp
import com.kyudong3.searchbookexample.utils.provider.ResourceProvider
import com.kyudong3.searchbookexample.utils.provider.impl.ResourceProviderImpl
import dagger.Module
import dagger.Provides
import dagger.Reusable


@Module
object ApplicationModule {

    @Provides
    @Reusable
    fun provideResourceProvider(): ResourceProvider =
        ResourceProviderImpl(SearchBookApp.instance.applicationContext)
}
