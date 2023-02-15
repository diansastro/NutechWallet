package com.widi.nutechwallet.view.topup

import com.widi.nutechwallet.base.BasePresenter
import com.widi.nutechwallet.data.body.TrxBody
import com.widi.nutechwallet.data.entity.TrxEntity
import com.widi.nutechwallet.objects.NetworkCode
import javax.inject.Inject

/**
 * Created by widi (widiytk@gmail.com) on 13/02/23.
 **/

class TopUpPresenter @Inject constructor(val trxEntity: TrxEntity): BasePresenter<TopUpContract.View>(), TopUpContract.Presenter {

    override fun execTopUp(trxBody: TrxBody) {
        addSubscription(trxEntity.execTopUp({
            if (it.code() == NetworkCode.CODE_OK) {
                when (it.body()?.status) {
                    NetworkCode.CODE_SUCCESS -> {
                        view?.onSuccess()
                    }
                    NetworkCode.TOKEN_EXPIRED -> {
                        view?.errorScreen("Token Kadaluarsa")
                    }
                    else -> {
                        view?.errorScreen("Gagal, Coba Lagi")
                    }
                }
            }
        }, {
           view?.errorScreen(it.localizedMessage)
        }, {}, trxBody))
    }
}