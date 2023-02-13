package com.widi.nutechwallet.data.exception

import android.content.Context
import java.io.IOException

/**
 * Created by widi (widiytk@gmail.com) on 13/02/23.
 **/

class TokenInvalidException(private val context: Context): IOException("Invalid Token") {
}