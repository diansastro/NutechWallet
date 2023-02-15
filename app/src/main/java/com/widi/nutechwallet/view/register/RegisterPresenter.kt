package com.widi.nutechwallet.view.register

import com.widi.nutechwallet.base.BasePresenter
import com.widi.nutechwallet.data.body.RegistBody
import com.widi.nutechwallet.data.entity.UserEntity
import com.widi.nutechwallet.objects.NetworkCode
import javax.inject.Inject

/**
 * Created by widi (widiytk@gmail.com) on 12/02/23.
 **/

class RegisterPresenter @Inject constructor(val userEntity: UserEntity): BasePresenter<RegisterContract.View>(), RegisterContract.Presenter {

    override fun execRegist(registBody: RegistBody) {
        addSubscription(userEntity.execRegist({
            if (it.code() == NetworkCode.CODE_OK) {
                when (it.body()?.status) {
                    NetworkCode.CODE_EMAIL_EXISTS -> {
                        view?.errorScreen("Gagal, Email Sudah Terdaftar")
                    }
                    NetworkCode.CODE_SUCCESS -> {
                        view?.onNextScreen()
                    }
                    else -> {
                        view?.errorScreen("Gagal, Coba Lagi")
                    }
                }
            }
        }, {
           view?.errorScreen(it.localizedMessage)
        }, {}, registBody))
    }
}