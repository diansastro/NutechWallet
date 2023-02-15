package com.widi.nutechwallet.view.login

import com.widi.nutechwallet.base.BasePresenter
import com.widi.nutechwallet.data.body.LoginBody
import com.widi.nutechwallet.data.entity.AuthEntity
import com.widi.nutechwallet.objects.NetworkCode
import javax.inject.Inject

/**
 * Created by widi (widiytk@gmail.com) on 12/02/23.
 **/

class LoginPresenter @Inject constructor(val authEntity: AuthEntity): BasePresenter<LoginContract.View>(), LoginContract.Presenter {
    override fun execLogin(loginBody: LoginBody) {
        addSubscription(authEntity.execLogin({
            when(it.code()) {
                NetworkCode.CODE_OK -> view?.onNextScreen()
                else -> {
                    view?.onError()
                }
            }
        }, {
           view?.onError()
        }, {}, loginBody))
    }
}