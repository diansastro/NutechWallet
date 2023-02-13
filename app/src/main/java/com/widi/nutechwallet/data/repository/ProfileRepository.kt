package com.widi.nutechwallet.data.repository

import android.content.Context
import com.google.gson.Gson
import com.widi.nutechwallet.base.AbstractPreferences
import com.widi.nutechwallet.model.UserData
import com.widi.nutechwallet.objects.PrefKey
import com.widi.nutechwallet.objects.PrefName
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by widi (widiytk@gmail.com) on 13/02/23.
 **/

@Singleton
open class ProfileRepository @Inject constructor(context: Context, gson: Gson): AbstractPreferences(context, gson) {

    override fun getPreferencesGroup(): String = PrefName.PREF_TOKEN_NAME

    var userData: UserData? = null
        get() {
            if (null == field) field = getData(PrefKey.PREF_KEY_USER, UserData::class.java)
            return field
        }

        set(value) {
            field = value
            if (null == value) clearData(PrefKey.PREF_KEY_USER)
            else saveData(PrefKey.PREF_KEY_USER, value)
        }

    fun clearProfileRepo() {
        userData = null
    }
}