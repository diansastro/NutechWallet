package com.widi.nutechwallet.data.api

import com.widi.nutechwallet.data.body.LoginBody
import com.widi.nutechwallet.data.body.RegistBody
import com.widi.nutechwallet.data.body.TrxBody
import com.widi.nutechwallet.data.body.UpdateProfileBody
import com.widi.nutechwallet.data.response.*
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * Created by widi (widiytk@gmail.com) on 13/02/23.
 **/

interface GeneralApi {

    @POST("login")
    fun login(@Body loginBody: LoginBody): Observable<Response<AuthResponse>>

    @POST("registration")
    fun registration(@Body registBody: RegistBody): Observable<Response<RegisteredUserResponse>>

    @GET("getProfile")
    fun getProfile(): Observable<Response<RegisteredUserResponse>>

    @POST("updateProfile")
    fun updateProfile(@Body updateProfileBody: UpdateProfileBody): Observable<Response<UpdateProfileResponse>>

    @GET("balance")
    fun getBalance(): Observable<Response<BalanceResponse>>

    @GET("transactionHistory")
    fun getTrxHistory(): Observable<Response<TrxHistoryListResponse>>

    @POST("topup")
    fun doTopUp(@Body trxBody: TrxBody): Observable<Response<TrxResponse>>

    @POST("transfer")
    fun doTransfer(@Body trxBody: TrxBody): Observable<Response<TrxResponse>>
}