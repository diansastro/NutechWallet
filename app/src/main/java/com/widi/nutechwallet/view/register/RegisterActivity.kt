package com.widi.nutechwallet.view.register

import androidx.core.content.ContextCompat
import com.jaeger.library.StatusBarUtil
import com.widi.nutechwallet.R
import com.widi.nutechwallet.base.BaseMvpActivity
import com.widi.nutechwallet.view.login.LoginActivity
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.intentFor
import javax.inject.Inject

/**
 * Created by widi (widiytk@gmail.com) on 12/02/23.
 **/

class RegisterActivity: BaseMvpActivity<RegisterPresenter>(), RegisterContract.View {

    @Inject
    override lateinit var presenter: RegisterPresenter

    override fun initPresenterView() {
        presenter.view = this
    }

    override fun injectView() {
        AndroidInjection.inject(this)
    }

    override fun setup() {
        StatusBarUtil.setColor(this, ContextCompat.getColor(this, R.color.red), 0)
        StatusBarUtil.setLightMode(this)
        initAction()
    }

    override fun getLayout(): Int = R.layout.activity_register

    private fun initAction() {
        tvBackToLogin.setOnClickListener {
            startActivity(intentFor<LoginActivity>())
            finish()
        }
    }
}