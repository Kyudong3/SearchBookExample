package com.kyudong3.searchbookexample

import com.facebook.stetho.Stetho
import com.kyudong3.searchbookexample.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication


class SearchBookApp : DaggerApplication() {

    // DaggerApplication의 메서드 구현(재정의) - AppComponent는 AndroidInjector를 상속했기에 반환Type에 캐스팅
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        // AppComponent 인스턴스 반환
        return DaggerAppComponent.create()
    }

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this)
        }
    }
}
