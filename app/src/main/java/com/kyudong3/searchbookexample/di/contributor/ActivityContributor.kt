package com.kyudong3.searchbookexample.di.contributor

import com.kyudong3.searchbookexample.MainActivity
import com.kyudong3.searchbookexample.di.scope.ActivityScope
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityContributor {

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun provideMainActivity(): MainActivity
}
