package com.widi.nutechwallet.data.response

import com.google.gson.annotations.SerializedName

/**
 * Created by widi (widiytk@gmail.com) on 16/02/23.
 **/

class TrxResponse(@SerializedName("status") val status: Int? = 0,
                  @SerializedName("message") val message: String? = "",
                  @SerializedName("data") val data: String? = "") {
}