package com.widi.nutechwallet.header

import android.content.Context
import com.google.gson.Gson
import com.widi.nutechwallet.base.AbstractPreferences
import com.widi.nutechwallet.data.repository.ProfileRepository
import com.widi.nutechwallet.model.TokenData
import com.widi.nutechwallet.model.UserData
import com.widi.nutechwallet.objects.PrefKey
import com.widi.nutechwallet.objects.PrefName
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by widi (widiytk@gmail.com) on 13/02/23.
 **/

@Singleton
open class HeaderManager @Inject constructor(val profileRepository: ProfileRepository, val context: Context, gson: Gson): AbstractPreferences(context, gson) {

    override fun getPreferencesGroup(): String = PrefName.PREF_TOKEN_NAME

    var accessToken: String? = ""
    var balance: String? = ""

    fun hasToken(): Boolean {
        return null != getToken()
    }

    fun isLoggedIn(): Boolean {
        return null != profileRepository.userData?.token
    }

    fun getToken(): String? {
        return profileRepository.userData?.token
    }

    fun getBearerToken(): String {
        return "Bearer ${getToken()}"
    }

    fun clearToken() {
        accessToken = null
    }

    fun logout() {
        clearToken()
        profileRepository.clearProfileRepo()
    }
}