package com.widi.nutechwallet.view.topup

import com.widi.nutechwallet.base.ErrorView
import com.widi.nutechwallet.data.body.TopUpBody

/**
 * Created by widi (widiytk@gmail.com) on 13/02/23.
 **/

interface TopUpContract {
    interface View: ErrorView {
        fun onSuccess()
        fun onError()
    }

    interface Presenter {
        fun execTopUp(topUpBody: TopUpBody)
    }
}