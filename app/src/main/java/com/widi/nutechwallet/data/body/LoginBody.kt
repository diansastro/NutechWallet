package com.widi.nutechwallet.data.body

import com.google.gson.annotations.SerializedName

/**
 * Created by widi (widiytk@gmail.com) on 13/02/23.
 **/

class LoginBody(@SerializedName("email") var email: String? = "",
                @SerializedName("password") var password: String? = "") {
}