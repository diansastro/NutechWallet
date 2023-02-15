package com.widi.nutechwallet.data

import android.content.Context
import com.widi.nutechwallet.data.interceptor.BasicInterceptor
import com.widi.nutechwallet.data.interceptor.ContentTypeInterceptor
import com.widi.nutechwallet.header.HeaderManager
import okhttp3.OkHttpClient

/**
 * Created by widi (widiytk@gmail.com) on 13/02/23.
 **/

abstract class BasicAbstractNetwork<T>(val headerManager: HeaderManager, private val basicInterceptor: BasicInterceptor, private val context: Context): AbstractNetwork<T>() {

    override fun okHttpClientBuilder(builder: OkHttpClient.Builder): OkHttpClient.Builder {
        builder.addInterceptor(ContentTypeInterceptor())
        builder.addInterceptor(basicInterceptor)
        return super.okHttpClientBuilder(builder)
    }

    fun getNetworkService(): T {
        return networkService()
    }
}