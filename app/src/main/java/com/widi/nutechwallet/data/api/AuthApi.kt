package com.widi.nutechwallet.data.api

import com.widi.nutechwallet.data.body.LoginBody
import com.widi.nutechwallet.data.body.RegistBody
import com.widi.nutechwallet.data.response.AuthResponse
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Created by widi (widiytk@gmail.com) on 13/02/23.
 **/

interface AuthApi {

    @POST("login")
    fun login(@Body loginBody: LoginBody): Observable<Response<AuthResponse>>

    @POST("registration")
    fun registration(@Body registBody: RegistBody): Observable<Response<AuthResponse>>
}