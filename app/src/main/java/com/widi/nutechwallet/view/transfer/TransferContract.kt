package com.widi.nutechwallet.view.transfer

import com.widi.nutechwallet.base.ErrorView
import com.widi.nutechwallet.data.body.TrxBody

/**
 * Created by widi (widiytk@gmail.com) on 13/02/23.
 **/

interface TransferContract {
    interface View: ErrorView {
        fun onSuccess()
        fun onError()
    }

    interface Presenter {
        fun execTransfer(trxBody: TrxBody)
    }
}