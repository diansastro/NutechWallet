package com.widi.nutechwallet.objects

import java.text.NumberFormat
import java.util.*

/**
 * Created by widi (widiytk@gmail.com) on 20/02/23.
 **/

object CurrencyFormatter {
    fun Any.convertRupiah(): String {
        val localId = Locale("in", "ID")
        val formatter = NumberFormat.getCurrencyInstance(localId)
        return formatter.format(this).replaceAfter(",", "").replace(",", "")
    }
}