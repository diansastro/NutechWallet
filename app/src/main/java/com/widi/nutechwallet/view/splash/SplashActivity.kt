package com.widi.nutechwallet.view.splash

import com.widi.nutechwallet.R
import com.widi.nutechwallet.base.BaseMvpActivity
import com.widi.nutechwallet.view.home.HomeActivity
import com.widi.nutechwallet.view.login.LoginActivity
import dagger.android.AndroidInjection
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.intentFor
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Created by widi (widiytk@gmail.com) on 12/02/23.
 **/

class SplashActivity: BaseMvpActivity<SplashPresenter>(), SplashContract.View {

    @Inject
    override lateinit var presenter: SplashPresenter

    override fun initPresenterView() {
        presenter.view = this
    }

    override fun injectView() {
        AndroidInjection.inject(this)
    }

    override fun setup() {
        initSplash()
    }

    private fun initSplash() = addUiSubscription(
        Observable.timer(2, TimeUnit.SECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { onNextScreen() }
    )

    private fun onNextScreen() {
        if(presenter.headerManager.isLoggedIn()){
            startActivity(intentFor<HomeActivity>())
            finish()
        } else {
            startActivity(intentFor<LoginActivity>())
            finish()
        }
    }

    override fun getLayout(): Int = R.layout.activity_splash
}