package com.widi.nutechwallet.view.login

import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.jaeger.library.StatusBarUtil
import com.jakewharton.rxbinding3.widget.textChanges
import com.widi.nutechwallet.R
import com.widi.nutechwallet.base.BaseMvpActivity
import com.widi.nutechwallet.data.body.LoginBody
import com.widi.nutechwallet.extension.isEmpty
import com.widi.nutechwallet.view.home.HomeActivity
import com.widi.nutechwallet.view.register.RegisterActivity
import dagger.android.AndroidInjection
import io.reactivex.android.schedulers.AndroidSchedulers
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

    override fun initSubscription() {
        addUiSubscription(etLoginEmail.textChanges().observeOn(AndroidSchedulers.mainThread()).subscribe {
            if (it.isNotEmpty()) {
                checkMandatory()
            }
        })

        addUiSubscription(etLoginPassword.textChanges().observeOn(AndroidSchedulers.mainThread()).subscribe {
            if (it.isNotEmpty()) {
                checkMandatory()
            }
        })

        super.initSubscription()
    }

    private fun initView() {
        checkMandatory()
    }

    private fun initAction() {
        tvRegister.setOnClickListener {
            startActivity(intentFor<RegisterActivity>())
            finish()
        }

        btnLogin.setOnClickListener {
            startActivity(intentFor<HomeActivity>())
//            if (!etLoginEmail.isEmpty() && !etLoginPassword.isEmpty()) {
//                presenter.execLogin(LoginBody(etLoginEmail.text.toString(), etLoginPassword.text.toString()))
//            }
        }
    }

    override fun loginSuccess() {

    }

    override fun onNextScreen() {
//        startActivity(intentFor<HomeActivity>())
        finish()
    }

    override fun onError() {
        Toast.makeText(this, "Username / Password Salah", Toast.LENGTH_SHORT).show()
    }

    private fun checkMandatory() {
        btnLogin.isEnabled = (etLoginEmail.text.isNotEmpty() && etLoginPassword.text.isNotEmpty())
    }
}