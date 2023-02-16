package com.widi.nutechwallet.view.profile

import android.widget.Toast
import androidx.core.content.ContextCompat
import com.jaeger.library.StatusBarUtil
import com.jakewharton.rxbinding3.widget.textChanges
import com.widi.nutechwallet.R
import com.widi.nutechwallet.base.BaseMvpActivity
import com.widi.nutechwallet.data.body.UpdateProfileBody
import com.widi.nutechwallet.data.response.RegisteredUserResponse
import com.widi.nutechwallet.model.RegisteredUserData
import com.widi.nutechwallet.view.dialog.SuccessDialog
import dagger.android.AndroidInjection
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.top_nav_profile.*
import javax.inject.Inject

/**
 * Created by widi (widiytk@gmail.com) on 13/02/23.
 **/

class ProfileActivity: BaseMvpActivity<ProfilePresenter>(), ProfileContract.View  {

    @Inject
    override lateinit var presenter: ProfilePresenter
    private var userData = arrayListOf<RegisteredUserData>()

    override fun initPresenterView() {
        presenter.view = this
    }

    override fun injectView() {
        AndroidInjection.inject(this)
    }

    override fun setup() {
        StatusBarUtil.setColor(this, ContextCompat.getColor(this, R.color.red), 0)
        StatusBarUtil.setLightMode(this)
        showLoading()
        presenter.execGetProfile()
        checkMandatory()
        initAction()
    }

    override fun getLayout(): Int = R.layout.activity_profile

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 0 ) super.onBackPressed()
        else supportFragmentManager.popBackStack()
    }

    override fun initSubscription() {
        addUiSubscription(etUserFirstName.textChanges().observeOn(AndroidSchedulers.mainThread()).subscribe {
            if (it.isNotEmpty()) {
                checkMandatory()
            }
        })

        addUiSubscription(etUserLastName.textChanges().observeOn(AndroidSchedulers.mainThread()).subscribe {
            if (it.isNotEmpty()) {
                checkMandatory()
            }
        })
        super.initSubscription()
    }

    private fun initAction() {
        tvAccountBack.setOnClickListener { onBackPressed() }
        refreshUserData.setOnRefreshListener {
            userData.clear()
            presenter.execGetProfile()
        }

        btnUpdateProfile.setOnClickListener {
            showLoading()
            presenter.execUpdateProfile(UpdateProfileBody(etUserFirstName.text.toString(), etUserLastName.text.toString()))
        }
    }

    override fun getProfile(registeredUserResponse: RegisteredUserResponse?) {
        userData.add(registeredUserResponse?.data!!)
        userData.forEach {
            etUserEmail.setText(getString(R.string.email, it.email))
            etUserFirstName.setText(getString(R.string.first_name, it.first_name))
            etUserLastName.setText(getString(R.string.first_name, it.last_name))
        }

        dismissLoading()
        refreshUserData.isRefreshing = false
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
        btnUpdateProfile.isEnabled = (etUserFirstName.text.isNotEmpty() && etUserLastName.text.isNotEmpty())
    }
}