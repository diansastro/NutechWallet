package com.widi.nutechwallet.extension

import android.widget.EditText

/**
 * Created by widi (widiytk@gmail.com) on 13/02/23.
 **/

fun EditText.isEmpty(): Boolean {
    return this.text.isNullOrEmpty()
}