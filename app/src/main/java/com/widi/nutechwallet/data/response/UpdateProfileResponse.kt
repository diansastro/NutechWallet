package com.widi.nutechwallet.data.response

import com.google.gson.annotations.SerializedName
import com.widi.nutechwallet.data.base.BaseResponse
import com.widi.nutechwallet.model.UpdateProfileData

/**
 * Created by widi (widiytk@gmail.com) on 16/02/23.
 **/

class UpdateProfileResponse(@SerializedName("data") var data: UpdateProfileData? = null ): BaseResponse() {
}