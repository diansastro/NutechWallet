package com.widi.nutechwallet.view.history

import com.widi.nutechwallet.base.BasePresenter
import com.widi.nutechwallet.data.entity.TrxEntity
import com.widi.nutechwallet.extension.uiSubscribe
import com.widi.nutechwallet.objects.NetworkCode
import javax.inject.Inject

/**
 * Created by widi (widiytk@gmail.com) on 13/02/23.
 **/

class HistoryPresenter @Inject constructor(val trxEntity: TrxEntity): BasePresenter<HistoryContract.View>(), HistoryContract.Presenter {

    override fun execAllTrxHistory() {
        addSubscription(trxEntity.getTrxHistory().uiSubscribe({
            when(it.code()) {
                NetworkCode.CODE_OK -> {
                    view?.getAllTrxHistory(it.body())
                }
                else -> {
                    view?.errorScreen("Token Error atau Kadaluarsa")
                }
            }
        }, {
            view?.errorScreen(it.localizedMessage)
        }, {}))
    }
}