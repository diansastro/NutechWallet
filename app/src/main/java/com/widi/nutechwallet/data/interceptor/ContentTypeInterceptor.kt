package com.widi.nutechwallet.data.interceptor

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

/**
 * Created by widi (widiytk@gmail.com) on 13/02/23.
 **/

open class ContentTypeInterceptor: Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(addHeader(chain.request()))
    }

    private fun addHeader(oriRequest: Request): Request {
        return oriRequest.newBuilder().addHeader("Content-Type", "application/json").build()
    }
}