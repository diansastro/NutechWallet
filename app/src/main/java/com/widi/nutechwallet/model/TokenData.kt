package com.widi.nutechwallet.model

import com.google.gson.annotations.SerializedName

/**
 * Created by widi (widiytk@gmail.com) on 13/02/23.
 **/

data class TokenData(@SerializedName("token") var token: String? = "") {
}