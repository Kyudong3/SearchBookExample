package com.kyudong3.searchbookexample

import com.facebook.stetho.Stetho
import com.kyudong3.searchbookexample.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication


class SearchBookApp : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.create()
    }

    init {
        instance = this
    }

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this)
        }
    }

    companion object {
        lateinit var instance: SearchBookApp
            private set
    }
}
