package com.widi.nutechwallet.model

import com.google.gson.annotations.SerializedName

/**
 * Created by widi (widiytk@gmail.com) on 16/02/23.
 **/

class RegisteredUserData(@SerializedName("email") var email: String? = "",
                         @SerializedName("first_name") val first_name: String? = "",
                         @SerializedName("last_name") val last_name: String? = "") {
}