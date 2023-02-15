package com.widi.nutechwallet.data.response

import com.google.gson.annotations.SerializedName
import com.widi.nutechwallet.data.base.BaseResponse
import com.widi.nutechwallet.model.BalanceData

/**
 * Created by widi (widiytk@gmail.com) on 15/02/23.
 **/

class BalanceResponse(@SerializedName("data") var data: BalanceData? = null): BaseResponse() {
}