package com.widi.nutechwallet.base

import android.content.Context
import android.content.SharedPreferences
import com.f2prateek.rx.preferences.RxSharedPreferences
import com.google.gson.Gson
import rx.Observable

/**
 * Created by widi (widiytk@gmail.com) on 12/02/23.
 **/

abstract class AbstractPreferences(context: Context, gson: Gson) {
    val gson: Gson = gson
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var rxSharedPreferences: RxSharedPreferences

    init {
        initPreferences(context, getPreferencesGroup())
    }

    abstract fun getPreferencesGroup(): String

    private fun initPreferences(context: Context, preferencesGroup: String) {
        sharedPreferences = context.getSharedPreferences(preferencesGroup, 0)
        rxSharedPreferences = RxSharedPreferences.create(sharedPreferences)
    }

    protected fun getRxSharedPreferences(): RxSharedPreferences {
        return rxSharedPreferences
    }

    protected fun saveData(tag: String, value: String) {
        rxSharedPreferences.getString(tag).set(value)
    }

    protected fun <T> saveData(tag: String, obj: T) {
        saveData(tag, gson.toJson(obj))
    }

    protected fun <T> saveDataList(tag: String, objList: List<T>) {
        saveData(tag, gson.toJson(objList))
    }

    protected fun <T> getData(tag: String, classOfT: Class<T>): T {
        return try {
            val rawData = rxSharedPreferences.getString(tag).get()
            gson.fromJson(rawData, classOfT)
        } catch (_: Exception) {
            clearData(tag)
            getData(tag, classOfT)
        }
    }

    protected fun <T> getDataAsObservable(tag: String, classOfT: Class<T>): Observable<T> {
        return rxSharedPreferences.getString(tag)
            .asObservable()
            .map { rawData -> gson.fromJson(rawData, classOfT) }
    }

    fun clearData(tag: String): Boolean {
        if (sharedPreferences != null) {
            sharedPreferences.edit()?.remove(tag)?.apply()
            return true
        }
        return false
    }

    protected fun getString(tag: String): String? {
        return rxSharedPreferences.getString(tag, null)?.get()
    }
}