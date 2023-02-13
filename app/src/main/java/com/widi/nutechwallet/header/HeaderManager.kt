package com.widi.nutechwallet.header

import android.content.Context
import com.google.gson.Gson
import com.widi.nutechwallet.base.AbstractPreferences
import com.widi.nutechwallet.data.repository.ProfileRepository
import com.widi.nutechwallet.model.TokenData
import com.widi.nutechwallet.objects.PrefKey
import com.widi.nutechwallet.objects.PrefName
import okhttp3.Credentials
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by widi (widiytk@gmail.com) on 13/02/23.
 **/

@Singleton
open class HeaderManager @Inject constructor(val profileRepository: ProfileRepository, val context: Context, gson: Gson): AbstractPreferences(context, gson) {

    override fun getPreferencesGroup(): String = PrefName.PREF_TOKEN_NAME

    var accessToken: TokenData? = null
    get() {
        if (null == field) field = getData(PrefKey.PREF_KEY_TOKEN, TokenData::class.java)
        return field
    }

    set(value) {
        field = value
        if (null == value) clearData(PrefKey.PREF_KEY_TOKEN)
        else saveData(PrefKey.PREF_KEY_TOKEN, value)
    }

    fun hasToken(): Boolean {
        return null != getToken()
    }

    fun isLoggedIn(): Boolean {
        return null != accessToken && null != profileRepository.userData
    }

    fun getToken(): TokenData? {
        return accessToken
    }

    fun getBearerToken(): String {
        return "Bearer ${getToken()?.token}"
    }

//    fun getBasicCredential(): String {
//        if (null != profileRepository.userData) {
//            return Credentials.basic(profileRepository.userData?.username!!, profileRepository.userData?.clientSecret!!)
//        }
//        return ""
//    }

    fun clearToken() {
        accessToken = null
    }

    fun logout() {
        clearToken()
        profileRepository.clearProfileRepo()
    }
}