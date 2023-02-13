package com.widi.nutechwallet.view.login

import android.view.View
import androidx.core.content.ContextCompat
import com.jaeger.library.StatusBarUtil
import com.widi.nutechwallet.R
import com.widi.nutechwallet.base.BaseMvpActivity
import com.widi.nutechwallet.view.home.HomeActivity
import com.widi.nutechwallet.view.register.RegisterActivity
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.intentFor
import javax.inject.Inject

/**
 * Created by widi (widiytk@gmail.com) on 12/02/23.
 **/

class LoginActivity: BaseMvpActivity<LoginPresenter>(), LoginContract.View {

    @Inject
    override lateinit var presenter: LoginPresenter

    override fun initPresenterView() {
        presenter.view = this
    }

    override fun injectView() {
        AndroidInjection.inject(this)
    }

    override fun setup() {
        StatusBarUtil.setColor(this, ContextCompat.getColor(this, R.color.red), 0)
        StatusBarUtil.setLightMode(this)
        initView()
        initAction()
    }

    override fun getLayout(): Int = R.layout.activity_login

    private fun initView() {

    }

    private fun initAction() {
        tvRegister.setOnClickListener {
            startActivity(intentFor<RegisterActivity>())
            finish()
        }

        btnLogin.setOnClickListener {
            startActivity(intentFor<HomeActivity>())
            finish()
        }
    }
}