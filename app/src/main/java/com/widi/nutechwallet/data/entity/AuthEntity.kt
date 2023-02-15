package com.widi.nutechwallet.data.entity

import com.widi.nutechwallet.data.AbstractNetwork
import com.widi.nutechwallet.data.api.AuthApi
import com.widi.nutechwallet.data.body.LoginBody
import com.widi.nutechwallet.data.body.RegistBody
import com.widi.nutechwallet.data.response.AuthResponse
import com.widi.nutechwallet.extension.uiSubscribe
import com.widi.nutechwallet.header.HeaderManager
import com.widi.nutechwallet.objects.NetworkCode
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import retrofit2.Response
import javax.inject.Inject

/**
 * Created by widi (widiytk@gmail.com) on 13/02/23.
 **/

open class AuthEntity @Inject constructor(var headerManager: HeaderManager): AbstractNetwork<AuthApi>() {

    override fun getApi(): Class<AuthApi> = AuthApi::class.java

    private fun loginInquiry(loginBody: LoginBody): Observable<Response<AuthResponse>> = networkService().login(loginBody)

    fun execLogin(onNext: (Response<AuthResponse>) -> Unit = {},
                  onError: (Throwable) -> Unit = {},
                  onComplete: () -> Unit = {},
                  loginBody: LoginBody): Disposable {
        return loginInquiry(loginBody).uiSubscribe({
            if (it.code() == NetworkCode.CODE_OK) {
                it.body()?.data?.let { data ->
                    headerManager.profileRepository.userData = data
                }
            }
            onNext.invoke(it)
        }, onError, onComplete)
    }

    fun regist(registBody: RegistBody): Observable<Response<AuthResponse>> = networkService().registration(registBody)

    fun execRegist(onNext: (Response<AuthResponse>) -> Unit = {},
                  onError: (Throwable) -> Unit = {},
                  onComplete: () -> Unit = {},
                  registBody: RegistBody): Disposable {
        return regist(registBody).uiSubscribe({
            onNext.invoke(it)
        }, onError, onComplete)
    }
}