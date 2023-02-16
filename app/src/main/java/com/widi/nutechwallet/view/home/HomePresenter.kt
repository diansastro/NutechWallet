package com.widi.nutechwallet.view.home

import com.widi.nutechwallet.base.BasePresenter
import com.widi.nutechwallet.data.entity.TrxEntity
import com.widi.nutechwallet.extension.uiSubscribe
import com.widi.nutechwallet.objects.NetworkCode
import javax.inject.Inject

/**
 * Created by widi (widiytk@gmail.com) on 12/02/23.
 **/

class HomePresenter @Inject constructor(val trxEntity: TrxEntity): BasePresenter<HomeContract.View>(), HomeContract.Presenter {

    override fun execBalance() {
        addSubscription(trxEntity.getBalance().uiSubscribe({
           when(it.code()) {
               NetworkCode.CODE_OK -> {
                   view?.getBalance(it.body())
               }
               else -> {
                   view?.errorScreen("Token Error atau Kadaluarsa")
               }
           }
        }, {
           view?.errorScreen(it.localizedMessage)
        }, {}))
    }

    override fun execTrxHistory() {
        addSubscription(trxEntity.getTrxHistory().uiSubscribe({
            if (it.code() == NetworkCode.NOT_FOUND) {
                view?.errorScreen("Data history tidak ditemukan")
            } else if (it.code() == NetworkCode.CODE_OK) {
                when(it.body()?.status) {
                    NetworkCode.CODE_SUCCESS -> {
                        view?.getTrxHistory(it.body())
                    }
                    else -> {
                        view?.errorScreen("Token Error atau Kadaluarsa")
                    }
                }
            }
        }, {
           view?.errorScreen(it.localizedMessage)
        }, {}))
    }
}