package com.widi.nutechwallet.model

import com.google.gson.annotations.SerializedName

/**
 * Created by widi (widiytk@gmail.com) on 15/02/23.
 **/

data class BalanceData(@SerializedName("balance") var balance: String? = "") {
}