package com.kyudong3.searchbookexample.utils.delegator

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable


class DisposableDelegatorImpl : DisposableDelegator {

    private val compositeDisposable by lazy { CompositeDisposable() }

    override fun <T : Disposable> T.add() {
        compositeDisposable.add(this)
    }

    override fun <T : Any> Observable<T>.baseSubscribe(
        onNext: (T) -> Unit,
        onError: (Throwable) -> Unit,
        onComplete: () -> Unit
    ) = subscribe(onNext, onError, onComplete).add()

    override fun <T : Any> Observable<T>.baseSubscribe(
        onNext: (T) -> Unit,
        onError: (Throwable) -> Unit
    ) = baseSubscribe(onNext, onError) {}

    override fun <T : Any> Observable<T>.baseSubscribe(
        onNext: (T) -> Unit
    ) = baseSubscribe(onNext) {}

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

    override fun <T : Any> Flowable<T>.baseSubscribe(
        onSuccess: (T) -> Unit
    ) = baseSubscribe(onSuccess) {}

    override fun <T : Any> Flowable<T>.baseSubscribe(
        onSuccess: (T) -> Unit,
        onError: (Throwable) -> Unit
    ) = subscribe(onSuccess, onError).add()

    override fun clearDisposable() {
        compositeDisposable.clear()
    }
}
