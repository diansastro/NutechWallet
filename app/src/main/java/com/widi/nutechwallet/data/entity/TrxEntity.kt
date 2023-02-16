package com.widi.nutechwallet.data.entity

import android.content.Context
import com.widi.nutechwallet.data.AuthAbstractNetwork
import com.widi.nutechwallet.data.api.GeneralApi
import com.widi.nutechwallet.data.body.TrxBody
import com.widi.nutechwallet.data.body.UpdateProfileBody
import com.widi.nutechwallet.data.interceptor.AuthInterceptor
import com.widi.nutechwallet.data.response.*
import com.widi.nutechwallet.extension.uiSubscribe
import com.widi.nutechwallet.header.HeaderManager
import com.widi.nutechwallet.objects.NetworkCode
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

    private fun doTopUp(trxBody: TrxBody): Observable<Response<TrxResponse>> = networkService().doTopUp(trxBody)

    fun execTopUp(onNext: (Response<TrxResponse>) -> Unit = {},
                  onError: (Throwable) -> Unit = {},
                  onComplete: () -> Unit = {},
                  trxBody: TrxBody): Disposable {
        return doTopUp(trxBody).uiSubscribe({
            onNext.invoke(it)
        }, onError, onComplete)
    }

    private fun doTransfer(trxBody: TrxBody): Observable<Response<TrxResponse>> = networkService().doTransfer(trxBody)

    fun execTransfer(onNext: (Response<TrxResponse>) -> Unit = {},
                  onError: (Throwable) -> Unit = {},
                  onComplete: () -> Unit = {},
                  trxBody: TrxBody): Disposable {
        return doTransfer(trxBody).uiSubscribe({
            onNext.invoke(it)
        }, onError, onComplete)
    }

    fun getProfile(): Observable<Response<RegisteredUserResponse>> = networkService().getProfile()

    fun doUpdateProfile(updateProfileBody: UpdateProfileBody): Observable<Response<UpdateProfileResponse>> = networkService().updateProfile(updateProfileBody)

    fun execUpdateProfile(onNext: (Response<UpdateProfileResponse>) -> Unit = {},
                        onError: (Throwable) -> Unit = {},
                        onComplete: () -> Unit = {},
                        updateProfileBody: UpdateProfileBody): Disposable {
        return doUpdateProfile(updateProfileBody).uiSubscribe({
            if (it.code() == NetworkCode.CODE_OK) {
                it.body()?.data?.let { data ->
                    val d = headerManager.profileRepository.userData
                    d?.first_name = data.first_name
                    d?.last_name = data.last_name
                }
            }
            onNext.invoke(it)
        }, onError, onComplete)
    }
}