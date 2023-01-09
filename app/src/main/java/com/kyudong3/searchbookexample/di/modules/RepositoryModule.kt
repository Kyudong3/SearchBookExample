package com.kyudong3.searchbookexample.di.modules

import com.kyudong3.searchbookexample.repository.SearchBookRepository
import com.kyudong3.searchbookexample.repository.impl.SearchBookRepositoryImpl
import com.kyudong3.searchbookexample.utils.provider.ApiProvider
import dagger.Module
import dagger.Provides
import dagger.Reusable


@Module
object RepositoryModule {

    @Provides
    @Reusable
    internal fun provideSearchBookRepository(
        apiProvider: ApiProvider
    ): SearchBookRepository = SearchBookRepositoryImpl(apiProvider)
}
