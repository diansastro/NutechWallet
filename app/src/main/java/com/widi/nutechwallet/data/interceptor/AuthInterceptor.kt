package com.widi.nutechwallet.data.interceptor

import android.content.Context
import com.widi.nutechwallet.data.exception.TokenInvalidException
import com.widi.nutechwallet.header.HeaderManager
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.net.HttpURLConnection
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.jvm.Throws

/**
 * Created by widi (widiytk@gmail.com) on 13/02/23.
 **/

@Singleton
class AuthInterceptor @Inject constructor(val headerManager: HeaderManager, val context: Context): Interceptor {

    val AUTHORIZATION = "Authorization"

    override fun intercept(chain: Interceptor.Chain): Response {
        val oriResponse = chain.request()

        if (headerManager.hasToken()) {
            var response = chain.proceed(addHeaderAuth(oriResponse))
            if (response.code() != 401) {
                return response
            } else {
                response = chain.proceed(addHeaderAuth(oriResponse))
                doWhenForbidden(response)
            }
            return response
        } else {
            return chain.proceed(addHeaderAuth(oriResponse))
        }
    }

    @Throws(TokenInvalidException::class)
    private fun doWhenForbidden(response: Response) {
        val code = response.code()
        if (code == HttpURLConnection.HTTP_FORBIDDEN) {
            throw TokenInvalidException(context)
        } else if (code == HttpURLConnection.HTTP_UNAUTHORIZED) {
            if (headerManager.isLoggedIn()) headerManager.logout()
            else headerManager.clearToken()
        }
    }

    private fun addHeaderAuth(oriRequest: Request): Request {
        return oriRequest.newBuilder()
            .addHeader(AUTHORIZATION,headerManager.getBearerToken())
            .build()
    }
}