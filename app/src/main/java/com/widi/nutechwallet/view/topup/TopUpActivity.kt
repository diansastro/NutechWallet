package com.widi.nutechwallet.view.topup

import android.widget.Toast
import androidx.core.content.ContextCompat
import com.jaeger.library.StatusBarUtil
import com.jakewharton.rxbinding3.widget.textChanges
import com.widi.nutechwallet.R
import com.widi.nutechwallet.base.BaseMvpActivity
import com.widi.nutechwallet.data.body.TopUpBody
import com.widi.nutechwallet.view.dialog.SuccessDialog
import dagger.android.AndroidInjection
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_topup.*
import kotlinx.android.synthetic.main.top_nav_topup.*
import javax.inject.Inject

/**
 * Created by widi (widiytk@gmail.com) on 13/02/23.
 **/

class TopUpActivity: BaseMvpActivity<TopUpPresenter>(), TopUpContract.View {

    @Inject
    override lateinit var presenter: TopUpPresenter

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

    override fun getLayout(): Int = R.layout.activity_topup

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 0 ) super.onBackPressed()
        else supportFragmentManager.popBackStack()
    }

    override fun initSubscription() {
        addUiSubscription(etTopUpAmount.textChanges().observeOn(AndroidSchedulers.mainThread()).subscribe {
            if (it.isNotEmpty()) {
                checkMandatory()
            }
        })
    }

    private fun initAction() {
        tvTopUpBack.setOnClickListener { onBackPressed() }
        btnTopUp.setOnClickListener {
            val t = etTopUpAmount.text.toString()
            val nom: Int = t.toInt()
            showLoading()
            presenter.execTopUp(TopUpBody(nom))
        }
    }

    override fun onSuccess() {
        dismissLoading()
        SuccessDialog.newInstance().show(supportFragmentManager, SuccessDialog::class.java.canonicalName)
    }

    override fun onError() {
        Toast.makeText(this, "Token Kadaluarsa, Coba Login Kembali", Toast.LENGTH_SHORT).show()
    }

    private fun checkMandatory() {
        btnTopUp.isEnabled = (etTopUpAmount.text.isNotEmpty())
    }
}