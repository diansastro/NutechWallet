package com.widi.nutechwallet.data.entity

import android.content.Context
import com.widi.nutechwallet.data.AuthAbstractNetwork
import com.widi.nutechwallet.data.api.GeneralApi
import com.widi.nutechwallet.data.body.TopUpBody
import com.widi.nutechwallet.data.interceptor.AuthInterceptor
import com.widi.nutechwallet.data.response.BalanceResponse
import com.widi.nutechwallet.data.response.TrxHistoryListResponse
import com.widi.nutechwallet.data.response.TrxResponse
import com.widi.nutechwallet.extension.uiSubscribe
import com.widi.nutechwallet.header.HeaderManager
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import retrofit2.Response
import javax.inject.Inject

/**
 * Created by widi (widiytk@gmail.com) on 15/02/23.
 **/

class TrxEntity @Inject constructor(headerManager: HeaderManager, authInterceptor: AuthInterceptor, context: Context): AuthAbstractNetwork<GeneralApi>(headerManager, authInterceptor, context) {

    override fun getApi(): Class<GeneralApi> = GeneralApi::class.java

    fun getBalance(): Observable<Response<BalanceResponse>> = networkService().getBalance()

    fun getTrxHistory(): Observable<Response<TrxHistoryListResponse>> = networkService().getTrxHistory()

    private fun doTopUp(topUpBody: TopUpBody): Observable<Response<TrxResponse>> = networkService().doTopUp(topUpBody)

    fun execTopUp(onNext: (Response<TrxResponse>) -> Unit = {},
                   onError: (Throwable) -> Unit = {},
                   onComplete: () -> Unit = {},
                   topUpBody: TopUpBody): Disposable {
        return doTopUp(topUpBody).uiSubscribe({
            onNext.invoke(it)
        }, onError, onComplete)
    }
}