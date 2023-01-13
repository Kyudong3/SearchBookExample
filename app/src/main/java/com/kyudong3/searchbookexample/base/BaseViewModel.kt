package com.kyudong3.searchbookexample.base

import androidx.databinding.Observable
import androidx.databinding.PropertyChangeRegistry
import androidx.lifecycle.ViewModel
import com.kyudong3.searchbookexample.utils.delegator.DisposableDelegator
import com.kyudong3.searchbookexample.utils.delegator.DisposableDelegatorImpl


abstract class BaseViewModel :
    ViewModel(),
    Observable,
    DisposableDelegator by DisposableDelegatorImpl() {

    @Transient
    private var callbacks: PropertyChangeRegistry? = null

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        synchronized(this) {
            if (callbacks == null) {
                callbacks = PropertyChangeRegistry()
            }
        }
        callbacks?.add(callback)
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        synchronized(this) {
            if (callbacks == null) {
                return
            }
        }
        callbacks?.remove(callback)
    }

    override fun onCleared() {
        clearDisposable()
        super.onCleared()
    }
}