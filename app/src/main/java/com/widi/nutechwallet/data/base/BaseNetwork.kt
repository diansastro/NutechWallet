package com.widi.nutechwallet.data.base

import com.google.gson.GsonBuilder
import io.reactivex.annotations.Nullable
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by widi (widiytk@gmail.com) on 13/02/23.
 **/

abstract class BaseNetwork<T> {
    @Nullable
    var service: T? = null

    private fun loggingInterceptor(): Interceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }

    open fun okHttpClientBuilder(builder: OkHttpClient.Builder): OkHttpClient.Builder = builder.addInterceptor(loggingInterceptor())

    private fun provideClient(): OkHttpClient = okHttpClientBuilder(OkHttpClient.Builder())
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()

    private fun gsonHandler(builder: GsonBuilder): GsonBuilder {
        return builder
    }

    private fun provideRetrofit(): Retrofit {
        val gson = this.gsonHandler(GsonBuilder().setPrettyPrinting()).setDateFormat("yyyy-MM-dd'T'hh:mm:ssZ").create()
        return Retrofit.Builder()
            .baseUrl(getBaseUrl())
            .client(provideClient())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    open fun networkService(): T {
        if (service == null) {
            val retrofit = provideRetrofit()
            RetrofitHelper.init(retrofit)
            service = retrofit.create(getApi())
        }
        return service!!
    }

    abstract fun getApi(): Class<T>

    abstract fun getBaseUrl(): String
}