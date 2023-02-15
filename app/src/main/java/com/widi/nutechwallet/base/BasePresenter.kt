package com.widi.nutechwallet.base

import com.widi.nutechwallet.header.HeaderManager
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import javax.inject.Inject

/**
 * Created by widi (widiytk@gmail.com) on 12/02/23.
 **/

open class BasePresenter<T: ErrorView> {
    @Inject
    lateinit var headerManager: HeaderManager

    var compose: CompositeDisposable = CompositeDisposable()

    var view:T ?= null

    open fun detachView(){
        this.view = null
        compose.clear()
    }

    fun addSubscription(disposable: Disposable) = compose.add(disposable)

    fun clearAllSubscription() = compose.clear()

    fun forceLogout() {
        headerManager.logout()
    }
}