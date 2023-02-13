package com.widi.nutechwallet.view.login

import com.widi.nutechwallet.base.BasePresenter
import com.widi.nutechwallet.data.body.LoginBody
import com.widi.nutechwallet.data.entity.GeneralEntity
import com.widi.nutechwallet.objects.NetworkCode
import javax.inject.Inject

/**
 * Created by widi (widiytk@gmail.com) on 12/02/23.
 **/

class LoginPresenter @Inject constructor(val generalEntity: GeneralEntity): BasePresenter<LoginContract.View>(), LoginContract.Presenter {
    override fun execLogin(loginBody: LoginBody) {
        addSubscription(generalEntity.execLogin({
            when(it.code()) {
                NetworkCode.CODE_OK -> view?.loginSuccess()
                else -> {
                    view?.onError()
                }
            }
        }, {
           view?.onError()
        }, {}, loginBody))
    }
}