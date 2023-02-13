package com.kyudong3.searchbookexample.utils.delegator

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.Disposable


interface DisposableDelegator {

    fun <T : Disposable> T.add()

    fun <T : Any> Observable<T>.baseSubscribe(
        onNext: (T) -> Unit,
        onError: (Throwable) -> Unit,
        onComplete: () -> Unit = {}
    )

    fun <T : Any> Observable<T>.baseSubscribe(
        onNext: (T) -> Unit,
        onError: (Throwable) -> Unit = { it.printStackTrace() }
    )

    fun <T : Any> Observable<T>.baseSubscribe(
        onNext: (T) -> Unit
    )

    fun <T : Any> Single<T>.baseSubscribe(
        onSuccess: (T) -> Unit,
        onError: (Throwable) -> Unit = { it.printStackTrace() }
    )

    fun <T : Any> Single<T>.baseSubscribe(
        onSuccess: (T) -> Unit
    )

    fun Completable.baseSubscribe(
        onComplete: () -> Unit,
        onError: (Throwable) -> Unit = { it.printStackTrace() }
    )

    fun Completable.baseSubscribe(
        onComplete: () -> Unit
    )

    fun clearDisposable()
}
