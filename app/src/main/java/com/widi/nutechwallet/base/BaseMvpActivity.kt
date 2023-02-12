package com.widi.nutechwallet.base

/**
 * Created by widi (widiytk@gmail.com) on 12/02/23.
 **/

abstract class BaseMvpActivity<T: BasePresenter<*>>: BaseActivity() {
    protected abstract var presenter: T

    override fun internalSetup() {
        initPresenterView()
        super.internalSetup()
    }

    abstract fun initPresenterView()

    override fun onDestroy() {
        presenter.detachView()
        super.onDestroy()
    }

    override fun forceLogout() {
        presenter.forceLogout()
        super.forceLogout()
    }
}