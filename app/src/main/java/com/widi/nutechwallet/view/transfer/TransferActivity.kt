package com.widi.nutechwallet.view.transfer

import android.widget.Toast
import androidx.core.content.ContextCompat
import com.jaeger.library.StatusBarUtil
import com.jakewharton.rxbinding3.widget.textChanges
import com.widi.nutechwallet.R
import com.widi.nutechwallet.base.BaseMvpActivity
import com.widi.nutechwallet.data.body.TrxBody
import com.widi.nutechwallet.view.dialog.SuccessDialog
import dagger.android.AndroidInjection
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_transfer.*
import kotlinx.android.synthetic.main.top_nav_transfer.*
import javax.inject.Inject

/**
 * Created by widi (widiytk@gmail.com) on 13/02/23.
 **/

class TransferActivity: BaseMvpActivity<TransferPresenter>(), TransferContract.View {

    @Inject
    override lateinit var presenter: TransferPresenter

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
        initView()
        initAction()
    }

    override fun getLayout(): Int  = R.layout.activity_transfer

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 0 ) super.onBackPressed()
        else supportFragmentManager.popBackStack()
    }

    override fun initSubscription() {
        addUiSubscription(etTransferAmount.textChanges().observeOn(AndroidSchedulers.mainThread()).subscribe {
            if (it.isNotEmpty()) {
                checkMandatory()
            }
        })
        super.initSubscription()
    }

    private fun initView() {
        presenter.headerManager.apply {
            tvCurrentBalance.text = getString(R.string.balance, this.balance)
        }
    }

    private fun initAction() {
        tvTransferBack.setOnClickListener { onBackPressed() }
        btnTransfer.setOnClickListener {
            val t = etTransferAmount.text.toString()
            val nom: Int = t.toInt()
            showLoading()
            presenter.execTransfer(TrxBody(nom))
        }
    }

    override fun onSuccess() {
        dismissLoading()
        SuccessDialog.newInstance().show(supportFragmentManager, SuccessDialog::class.java.canonicalName)
    }

    override fun onError() {
        Toast.makeText(this, "Token Kadaluarsa, Coba Login Kembali", Toast.LENGTH_SHORT).show()
        dismissLoading()
    }

    private fun checkMandatory() {
        btnTransfer.isEnabled = (etTransferAmount.text.isNotEmpty())
    }
}