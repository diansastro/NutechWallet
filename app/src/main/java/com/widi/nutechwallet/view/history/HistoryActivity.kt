package com.widi.nutechwallet.view.history

import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jaeger.library.StatusBarUtil
import com.widi.nutechwallet.R
import com.widi.nutechwallet.base.BaseMvpActivity
import com.widi.nutechwallet.data.response.TrxHistoryListResponse
import com.widi.nutechwallet.model.TrxData
import com.widi.nutechwallet.view.adapter.TrxHistoryAdapter
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_history.*
import kotlinx.android.synthetic.main.top_nav_history.*
import javax.inject.Inject

/**
 * Created by widi (widiytk@gmail.com) on 13/02/23.
 **/

class HistoryActivity: BaseMvpActivity<HistoryPresenter>(), HistoryContract.View {

    @Inject
    override lateinit var presenter: HistoryPresenter

    private lateinit var trxHistoryAdapter: TrxHistoryAdapter
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
        presenter.execAllTrxHistory()
        initAction()
    }

    override fun getLayout(): Int = R.layout.activity_history

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 0 ) super.onBackPressed()
        else supportFragmentManager.popBackStack()
    }

    private fun initAction() {
        tvHistoryBack.setOnClickListener { onBackPressed() }
        refreshTrxHistory.setOnRefreshListener {
            trxData.clear()
            presenter.execAllTrxHistory()
            for (i in 0 until rvTrxHistoryList.itemDecorationCount) {
                rvTrxHistoryList.removeItemDecorationAt(i)
            }
        }
    }

    override fun getAllTrxHistory(trxHistoryListResponse: TrxHistoryListResponse?) {
        trxData.addAll(trxHistoryListResponse?.data!!)

        trxHistoryAdapter = TrxHistoryAdapter(trxData)
        rvTrxHistoryList.apply {
            adapter = trxHistoryAdapter
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            isNestedScrollingEnabled = true
        }

        rvTrxHistoryList.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
        dismissLoading()
        refreshTrxHistory.isRefreshing = false
    }
}