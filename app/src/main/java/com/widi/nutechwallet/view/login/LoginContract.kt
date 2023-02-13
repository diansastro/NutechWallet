package com.widi.nutechwallet.view.login

import com.widi.nutechwallet.base.ErrorView
import com.widi.nutechwallet.data.body.LoginBody

/**
 * Created by widi (widiytk@gmail.com) on 12/02/23.
 **/

interface LoginContract {
    interface View: ErrorView {
        fun loginSuccess()
        fun onNextScreen()
        fun onError()
    }

    interface Presenter {
        fun execLogin(loginBody: LoginBody)
//        fun execPostRetrieveToken()
    }
}