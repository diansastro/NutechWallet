package com.widi.nutechwallet.data

import com.widi.nutechwallet.BuildConfig
import com.widi.nutechwallet.data.base.BaseNetwork
import com.widi.nutechwallet.data.interceptor.ContentTypeInterceptor
import okhttp3.OkHttpClient

/**
 * Created by widi (widiytk@gmail.com) on 13/02/23.
 **/

abstract class AbstractNetwork<T>(): BaseNetwork<T>() {

    override fun getBaseUrl(): String = BuildConfig.BASE_URL

    override fun okHttpClientBuilder(builder: OkHttpClient.Builder): OkHttpClient.Builder {
        builder.addInterceptor(ContentTypeInterceptor())
        return super.okHttpClientBuilder(builder)
    }
}