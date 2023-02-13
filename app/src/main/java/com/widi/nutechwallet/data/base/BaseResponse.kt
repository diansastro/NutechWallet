package com.widi.nutechwallet.data.base

import com.google.gson.annotations.SerializedName

/**
 * Created by widi (widiytk@gmail.com) on 13/02/23.
 **/

open class BaseResponse(@SerializedName("status") val status: Int? = 0,
                   @SerializedName("message") val message: String? = "") {
}