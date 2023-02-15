package com.widi.nutechwallet.view.home

import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView
import com.jaeger.library.StatusBarUtil
import com.widi.nutechwallet.R
import com.widi.nutechwallet.base.BaseMvpActivity
import com.widi.nutechwallet.data.response.BalanceResponse
import com.widi.nutechwallet.data.response.TrxHistoryListResponse
import com.widi.nutechwallet.model.TrxData
import com.widi.nutechwallet.view.adapter.TrxHistoryAdapter
import com.widi.nutechwallet.view.history.HistoryActivity
import com.widi.nutechwallet.view.login.LoginActivity
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

    private lateinit var trxHistoryAdapter: TrxHistoryAdapter
    private var balance = ""
    private val trxData = arrayListOf<TrxData>()

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
        presenter.execBalance()
        presenter.execTrxHistory()
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

            R.id.nav_item_logout -> {
                presenter.headerManager.logout()
                startActivity(intentFor<LoginActivity>())
                finish()
            }
        }

        dlHome.closeDrawer(GravityCompat.START)
        return true
    }

    private fun initView() {
        nav_view_home.setNavigationItemSelectedListener(this)
        presenter.headerManager.profileRepository.userData?.apply {
            val data = this
            tvUserName.text = getString(R.string.user, data.first_name?.plus(" ").plus(data.last_name))
        }
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

        tvViewAllTrx.setOnClickListener {
            startActivity(intentFor<HistoryActivity>())
        }

        refreshData.setOnRefreshListener {
            balance = ""
            trxData.clear()
            presenter.execBalance()
            presenter.execTrxHistory()
            for (i in 0 until rvTrxHistory.itemDecorationCount) {
                rvTrxHistory.removeItemDecorationAt(i)
            }
        }
    }

    private fun initDrawer() {
        drawerToggle = ActionBarDrawerToggle(this, dlHome, R.string.app_name, R.string.log_in)
        dlHome.addDrawerListener(drawerToggle)
        drawerToggle.syncState()
    }

    override fun getBalance(balanceResponse: BalanceResponse?) {
        balance = balanceResponse?.data?.balace.toString()
        if (balance.isNotBlank()) {
            tvTotalBalance.text = getString(R.string.balance, balance)
        } else {
            tvTotalBalance.text = "0"
        }

        dismissLoading()
        refreshData.isRefreshing = false
    }

    override fun getTrxHistory(trxHistoryListResponse: TrxHistoryListResponse?) {
        val dataSize = trxHistoryListResponse?.data?.size!!
        if (dataSize in 1..6) {
            trxData.addAll(trxHistoryListResponse.data)
        }

        trxHistoryAdapter = TrxHistoryAdapter(trxData)
        rvTrxHistory.apply {
            adapter = trxHistoryAdapter
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            isNestedScrollingEnabled = true
        }

        rvTrxHistory.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
        dismissLoading()
        refreshData.isRefreshing = false
    }
}