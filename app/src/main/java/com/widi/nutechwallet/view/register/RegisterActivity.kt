package com.widi.nutechwallet.view.register

import android.widget.Toast
import androidx.core.content.ContextCompat
import com.jaeger.library.StatusBarUtil
import com.jakewharton.rxbinding3.widget.textChanges
import com.widi.nutechwallet.R
import com.widi.nutechwallet.base.BaseMvpActivity
import com.widi.nutechwallet.data.body.RegistBody
import com.widi.nutechwallet.extension.isEmpty
import com.widi.nutechwallet.view.login.LoginActivity
import dagger.android.AndroidInjection
import io.reactivex.android.schedulers.AndroidSchedulers
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
        checkMandatory()
        initAction()
    }

    override fun getLayout(): Int = R.layout.activity_register

    override fun initSubscription() {
        addUiSubscription(etRegistEmail.textChanges().observeOn(AndroidSchedulers.mainThread()).subscribe {
            if (it.isNotEmpty()) {
                checkMandatory()
            }
        })

        addUiSubscription(etFirstName.textChanges().observeOn(AndroidSchedulers.mainThread()).subscribe {
            if (it.isNotEmpty()) {
                checkMandatory()
            }
        })

        addUiSubscription(etLastName.textChanges().observeOn(AndroidSchedulers.mainThread()).subscribe {
            if (it.isNotEmpty()) {
                checkMandatory()
            }
        })

        addUiSubscription(etPassword.textChanges().observeOn(AndroidSchedulers.mainThread()).subscribe {
            if (it.isNotEmpty()) {
                checkMandatory()
            }
        })

        super.initSubscription()
    }

    private fun initAction() {
        tvBackToLogin.setOnClickListener {
            startActivity(intentFor<LoginActivity>())
            finish()
        }

        btnRegister.setOnClickListener {
            if (!etRegistEmail.isEmpty() && !etFirstName.isEmpty() && !etLastName.isEmpty() && !etPassword.isEmpty()) {
                showLoading()
                presenter.execRegist(RegistBody(etRegistEmail.text.toString(), etFirstName.text.toString(), etLastName.text.toString(), etPassword.text.toString()))
            }
        }
    }

    override fun onNextScreen() {
        dismissLoading()
        Toast.makeText(this, "Berhasil, Silahkan Login", Toast.LENGTH_SHORT).show()
        startActivity(intentFor<LoginActivity>())
    }

    override fun onError() {
        Toast.makeText(this, "Email Sudah Terdaftar", Toast.LENGTH_SHORT).show()
    }

    private fun checkMandatory() {
        btnRegister.isEnabled = (etRegistEmail.text.isNotEmpty() && etFirstName.text.isNotEmpty() && etLastName.text.isNotEmpty() && etPassword.text.isNotEmpty())
    }
}