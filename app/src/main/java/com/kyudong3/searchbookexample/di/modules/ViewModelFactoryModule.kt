package com.kyudong3.searchbookexample.di.modules

import androidx.lifecycle.ViewModelProvider
import com.kyudong3.searchbookexample.viewmodels.ViewModelFactory
import dagger.Binds
import dagger.Module


@Module
abstract class ViewModelFactoryModule {
    @Binds
    abstract fun bindViewModelFactory(
        viewModelFactory: ViewModelFactory
    ): ViewModelProvider.Factory
}
