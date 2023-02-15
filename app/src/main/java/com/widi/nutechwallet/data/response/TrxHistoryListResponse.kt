package com.widi.nutechwallet.data.response

import com.google.gson.annotations.SerializedName
import com.widi.nutechwallet.data.base.BaseResponse
import com.widi.nutechwallet.model.TrxData

/**
 * Created by widi (widiytk@gmail.com) on 16/02/23.
 **/

class TrxHistoryListResponse(@SerializedName("data") var data: ArrayList<TrxData>): BaseResponse() {
}