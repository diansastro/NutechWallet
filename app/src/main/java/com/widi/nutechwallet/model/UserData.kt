package com.widi.nutechwallet.model

import com.google.gson.annotations.SerializedName

/**
 * Created by widi (widiytk@gmail.com) on 13/02/23.
 **/

data class UserData(@SerializedName("email") var email: String? = "",
                    @SerializedName("first_name") val first_name: String? = "",
                    @SerializedName("last_name") val last_name: String? = "",
                    @SerializedName("token") val token: TokenData? = null) {
}