package com.widi.nutechwallet.data.entity

import com.widi.nutechwallet.data.AbstractNetwork
import com.widi.nutechwallet.data.api.GeneralApi
import com.widi.nutechwallet.data.body.RegistBody
import com.widi.nutechwallet.data.response.AuthResponse
import com.widi.nutechwallet.data.response.RegisteredUserResponse
import com.widi.nutechwallet.extension.uiSubscribe
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import retrofit2.Response
import javax.inject.Inject

/**
 * Created by widi (widiytk@gmail.com) on 15/02/23.
 **/

class UserEntity @Inject constructor(): AbstractNetwork<GeneralApi>() {

    override fun getApi(): Class<GeneralApi> = GeneralApi::class.java

    fun regist(registBody: RegistBody): Observable<Response<RegisteredUserResponse>> = networkService().registration(registBody)

    fun execRegist(onNext: (Response<RegisteredUserResponse>) -> Unit = {},
                   onError: (Throwable) -> Unit = {},
                   onComplete: () -> Unit = {},
                   registBody: RegistBody): Disposable {
        return regist(registBody).uiSubscribe({
            onNext.invoke(it)
        }, onError, onComplete)
    }
}