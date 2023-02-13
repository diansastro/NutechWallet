package com.widi.nutechwallet.model

import com.google.gson.annotations.SerializedName

/**
 * Created by widi (widiytk@gmail.com) on 13/02/23.
 **/

data class TrxData(@SerializedName("transaction_id") var transaction_id: Int? = 0,
                   @SerializedName("transaction_time") var transaction_time: String? = "",
                   @SerializedName("transaction_type") var transaction_type: String? = "",
                   @SerializedName("amount") var amount: Int? = 0) {
}