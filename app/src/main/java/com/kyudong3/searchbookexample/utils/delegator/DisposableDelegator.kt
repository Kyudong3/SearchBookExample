package com.kyudong3.searchbookexample.utils.delegator

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.Disposable


interface DisposableDelegator {

    fun <T : Disposable> T.add()

    fun <T : Any> Single<T>.baseSubscribe(
        onSuccess: (T) -> Unit,
        onError: (Throwable) -> Unit = { it.printStackTrace() }
    )

    fun <T : Any> Single<T>.baseSubscribe(
        onSuccess: (T) -> Unit
    )

    fun clearDisposable()
}
