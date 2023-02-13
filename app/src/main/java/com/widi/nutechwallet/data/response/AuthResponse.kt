package com.widi.nutechwallet.data.response

import com.google.gson.annotations.SerializedName
import com.widi.nutechwallet.data.base.BaseResponse
import com.widi.nutechwallet.model.UserData

/**
 * Created by widi (widiytk@gmail.com) on 13/02/23.
 **/

class AuthResponse(@SerializedName("data") val data: UserData? = null): BaseResponse() {
}