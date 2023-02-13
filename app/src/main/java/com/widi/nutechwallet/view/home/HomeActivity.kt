package com.widi.nutechwallet.view.home

import androidx.core.content.ContextCompat
import com.jaeger.library.StatusBarUtil
import com.widi.nutechwallet.R
import com.widi.nutechwallet.base.BaseMvpActivity
import dagger.android.AndroidInjection
import javax.inject.Inject

/**
 * Created by widi (widiytk@gmail.com) on 12/02/23.
 **/

class HomeActivity: BaseMvpActivity<HomePresenter>(), HomeContract.View {

    @Inject
    override lateinit var presenter: HomePresenter

    override fun initPresenterView() {
        presenter.view = this
    }

    override fun injectView() {
        AndroidInjection.inject(this)
    }

    override fun setup() {
        StatusBarUtil.setColor(this, ContextCompat.getColor(this, R.color.red), 0)
        StatusBarUtil.setLightMode(this)
    }

    override fun getLayout(): Int = R.layout.activity_home
}