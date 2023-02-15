package com.widi.nutechwallet.view.history

import com.widi.nutechwallet.base.ErrorView
import com.widi.nutechwallet.data.response.TrxHistoryListResponse

/**
 * Created by widi (widiytk@gmail.com) on 13/02/23.
 **/

interface HistoryContract {
    interface View: ErrorView {
        fun getAllTrxHistory(trxHistoryListResponse: TrxHistoryListResponse?)
    }

    interface Presenter {
        fun execAllTrxHistory()
    }
}