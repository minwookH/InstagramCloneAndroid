package com.fancy.instagram.base

import androidx.lifecycle.ViewModel
import com.fancy.instagram.application.InstagramApp
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseViewModel(private val application: InstagramApp) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    protected fun getContext() = application

    abstract fun onCreate()

    fun Disposable.addDisposable() {
        compositeDisposable.add(this)
    }

    override fun onCleared() {
        compositeDisposable.dispose()
    }
}