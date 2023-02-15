package com.widi.nutechwallet.view.transfer

import com.widi.nutechwallet.base.BasePresenter
import com.widi.nutechwallet.data.body.TrxBody
import com.widi.nutechwallet.data.entity.TrxEntity
import com.widi.nutechwallet.objects.NetworkCode
import javax.inject.Inject

/**
 * Created by widi (widiytk@gmail.com) on 13/02/23.
 **/

class TransferPresenter @Inject constructor(val trxEntity: TrxEntity): BasePresenter<TransferContract.View>(), TransferContract.Presenter {

    override fun execTransfer(trxBody: TrxBody) {
        addSubscription(trxEntity.execTransfer({
            when(it.code()) {
                NetworkCode.CODE_OK -> {
                    view?.onSuccess()
                }
                NetworkCode.BAD_REQUEST -> {
                    view?.errorScreen("Saldo anda tidak mencukupi untuk melakukan transfer")
                }
                else -> {
                    view?.errorScreen("Gagal, Coba Lagi")
                }
            }
        }, {
            view?.errorScreen(it.localizedMessage)
        }, {}, trxBody))
    }
}