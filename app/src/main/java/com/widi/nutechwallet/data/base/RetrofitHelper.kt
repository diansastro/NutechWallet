package com.widi.nutechwallet.data.base

import retrofit2.Retrofit

/**
 * Created by widi (widiytk@gmail.com) on 13/02/23.
 **/

class RetrofitHelper {
    companion object {
        var retrofit: Retrofit? = null

        fun init(retrofit: Retrofit) {
            Companion.retrofit = retrofit
        }
    }
}