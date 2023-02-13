package com.widi.nutechwallet.view.home

import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import com.google.android.material.navigation.NavigationView
import com.jaeger.library.StatusBarUtil
import com.widi.nutechwallet.R
import com.widi.nutechwallet.base.BaseMvpActivity
import com.widi.nutechwallet.view.profile.ProfileActivity
import com.widi.nutechwallet.view.topup.TopUpActivity
import com.widi.nutechwallet.view.transfer.TransferActivity
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.top_nav_home.*
import org.jetbrains.anko.intentFor
import javax.inject.Inject

/**
 * Created by widi (widiytk@gmail.com) on 12/02/23.
 **/

class HomeActivity: BaseMvpActivity<HomePresenter>(), HomeContract.View, NavigationView.OnNavigationItemSelectedListener {

    @Inject
    override lateinit var presenter: HomePresenter

    private lateinit var drawerToggle : ActionBarDrawerToggle

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
        initDrawer()
        initAction()
    }

    override fun getLayout(): Int = R.layout.activity_home

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_item_profile -> {
                startActivity(intentFor<ProfileActivity>())
            }
        }

        dlHome.closeDrawer(GravityCompat.START)
        return true
    }

    private fun initView() {
        nav_view_home.setNavigationItemSelectedListener(this)
    }

    private fun initAction() {
        ivHamburger.setOnClickListener {
            dlHome.openDrawer(GravityCompat.START)
        }

        cardTransfer.setOnClickListener {
            startActivity(intentFor<TransferActivity>())
        }

        cardTopUp.setOnClickListener {
            startActivity(intentFor<TopUpActivity>())
        }
    }

    private fun initDrawer() {
        drawerToggle = ActionBarDrawerToggle(this, dlHome, R.string.app_name, R.string.log_in)
        dlHome.addDrawerListener(drawerToggle)
        drawerToggle.syncState()
    }

}