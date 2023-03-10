package com.widi.nutechwallet.view.register

import com.widi.nutechwallet.base.ErrorView
import com.widi.nutechwallet.data.body.RegistBody

/**
 * Created by widi (widiytk@gmail.com) on 12/02/23.
 **/

interface RegisterContract {
    interface View: ErrorView {
        fun onNextScreen()
        fun onError()
    }

    interface Presenter {
        fun execRegist(registBody: RegistBody)
    }
}