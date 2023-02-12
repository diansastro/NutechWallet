package com.widi.nutechwallet.base

/**
 * Created by widi (widiytk@gmail.com) on 12/02/23.
 **/

interface ErrorView {
    fun errorScreen(message: String? = "", code: Int? = -1)
    fun errorScreen(message: String? = "")
    fun forceLogout()
}