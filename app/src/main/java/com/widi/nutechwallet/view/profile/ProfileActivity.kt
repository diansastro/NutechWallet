package com.widi.nutechwallet.view.profile

import androidx.core.content.ContextCompat
import com.jaeger.library.StatusBarUtil
import com.widi.nutechwallet.R
import com.widi.nutechwallet.base.BaseMvpActivity
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.top_nav_profile.*
import javax.inject.Inject

/**
 * Created by widi (widiytk@gmail.com) on 13/02/23.
 **/

class ProfileActivity: BaseMvpActivity<ProfilePresenter>(), ProfileContract.View  {

    @Inject
    override lateinit var presenter: ProfilePresenter

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

    override fun getLayout(): Int = R.layout.activity_profile

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 0 ) super.onBackPressed()
        else supportFragmentManager.popBackStack()
    }

    private fun initAction() {
        tvAccountBack.setOnClickListener { onBackPressed() }
    }
}