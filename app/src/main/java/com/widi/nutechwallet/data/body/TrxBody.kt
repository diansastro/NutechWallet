package com.widi.nutechwallet.data.body

import com.google.gson.annotations.SerializedName

/**
 * Created by widi (widiytk@gmail.com) on 16/02/23.
 **/

class TrxBody(@SerializedName("amount") val amount: Int? = 0) {
}