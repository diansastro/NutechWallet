package com.widi.nutechwallet.data.interceptor

import android.content.Context
import com.widi.nutechwallet.header.HeaderManager
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by widi (widiytk@gmail.com) on 13/02/23.
 **/

@Singleton
class BasicInterceptor @Inject constructor(val headerManager: HeaderManager, val context: Context): Interceptor {

    val AUTHORIZATION = "Authorization"

    override fun intercept(chain: Interceptor.Chain): Response {
        val oriResponse = chain.request()

        if (null != headerManager.profileRepository.userData) {
            var response = chain.proceed(addHeaderAuth(oriResponse))
            if (response.code() != 400) {
                return response
            } else {
                response = chain.proceed(addHeaderAuth(oriResponse))
            }
            return response
        } else {
            return chain.proceed(oriResponse.newBuilder().build())
        }
    }

    private fun addHeaderAuth(oriRequest: Request): Request {
        return oriRequest.newBuilder()
            .addHeader(AUTHORIZATION, headerManager.accessToken.toString())
            .build()
    }
}