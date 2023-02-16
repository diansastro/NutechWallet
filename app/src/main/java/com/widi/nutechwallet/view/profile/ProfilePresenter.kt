package com.widi.nutechwallet.view.profile

import com.widi.nutechwallet.base.BasePresenter
import com.widi.nutechwallet.data.body.UpdateProfileBody
import com.widi.nutechwallet.data.entity.TrxEntity
import com.widi.nutechwallet.extension.uiSubscribe
import com.widi.nutechwallet.objects.NetworkCode
import javax.inject.Inject

/**
 * Created by widi (widiytk@gmail.com) on 13/02/23.
 **/

class ProfilePresenter @Inject constructor(val trxEntity: TrxEntity): BasePresenter<ProfileContract.View>(), ProfileContract.Presenter {

    override fun execGetProfile() {
        addSubscription(trxEntity.getProfile().uiSubscribe({
           when(it.code()) {
               NetworkCode.CODE_OK -> {
                   view?.getProfile(it.body())
               }
               else -> {
                   view?.errorScreen("Token Error atau Kadaluarsa")
               }
           }
        }, {
           view?.errorScreen(it.localizedMessage)
        }, {}))
    }

    override fun execUpdateProfile(updateProfileBody: UpdateProfileBody) {
        addSubscription(trxEntity.execUpdateProfile({
            if (it.code() == NetworkCode.CODE_OK) {
                when(it.body()?.status) {
                    NetworkCode.CODE_SUCCESS -> {
                        view?.onSuccess()
                    }
                    NetworkCode.TOKEN_EXPIRED -> {
                        view?.errorScreen("Token Error atau Kadaluarsa")
                    }
                    else -> {
                        view?.errorScreen("Gagal, Coba Lagi")
                    }
                }
            }
        }, {
           view?.errorScreen(it.localizedMessage)
        }, {}, updateProfileBody))
    }
}