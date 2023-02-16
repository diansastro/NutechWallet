package com.widi.nutechwallet.model

import com.google.gson.annotations.SerializedName

/**
 * Created by widi (widiytk@gmail.com) on 13/02/23.
 **/

data class UserData(@SerializedName("email") var email: String? = "",
                    @SerializedName("first_name") var first_name: String? = "",
                    @SerializedName("last_name") var last_name: String? = "",
                    @SerializedName("token") var token: String? = "") {
}