package com.widi.nutechwallet.view.home

import com.widi.nutechwallet.base.ErrorView
import com.widi.nutechwallet.data.response.BalanceResponse
import com.widi.nutechwallet.data.response.TrxHistoryListResponse

/**
 * Created by widi (widiytk@gmail.com) on 12/02/23.
 **/

interface HomeContract {
    interface View: ErrorView {
        fun getBalance(balanceResponse: BalanceResponse?)
        fun getTrxHistory(trxHistoryListResponse: TrxHistoryListResponse?)
    }

    interface Presenter {
        fun execBalance()
        fun execTrxHistory()
    }
}