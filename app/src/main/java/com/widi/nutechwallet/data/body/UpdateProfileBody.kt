package com.widi.nutechwallet.data.body

import com.google.gson.annotations.SerializedName

/**
 * Created by widi (widiytk@gmail.com) on 13/02/23.
 **/

class UpdateProfileBody(@SerializedName("first_name") var first_name: String? = "",
                        @SerializedName("last_name") var last_name: String? = "") {
}