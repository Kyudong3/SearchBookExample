package com.kyudong3.searchbookexample.utils.delegator

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable


class DisposableDelegatorImpl : DisposableDelegator {

    private val compositeDisposable by lazy { CompositeDisposable() }

    override fun <T : Disposable> T.add() {
        compositeDisposable.add(this)
    }

    override fun <T : Any> Single<T>.baseSubscribe(
        onSuccess: (T) -> Unit,
        onError: (Throwable) -> Unit
    ) = subscribe(onSuccess, onError).add()

    override fun <T : Any> Single<T>.baseSubscribe(
        onSuccess: (T) -> Unit
    ) = baseSubscribe(onSuccess) {}

    override fun Completable.baseSubscribe(
        onComplete: () -> Unit,
        onError: (Throwable) -> Unit
    ) = subscribe(onComplete, onError).add()

    override fun Completable.baseSubscribe(
        onComplete: () -> Unit
    ) = baseSubscribe(onComplete) {}

    override fun clearDisposable() {
        compositeDisposable.clear()
    }
}
