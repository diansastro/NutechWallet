package com.widi.nutechwallet.model

import com.google.gson.annotations.SerializedName

/**
 * Created by widi (widiytk@gmail.com) on 16/02/23.
 **/

data class RegisteredUserData(@SerializedName("email") var email: String? = "",
                         @SerializedName("first_name") var first_name: String? = "",
                         @SerializedName("last_name") var last_name: String? = "") {
}