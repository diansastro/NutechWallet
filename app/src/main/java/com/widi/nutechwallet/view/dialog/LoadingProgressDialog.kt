package com.widi.nutechwallet.view.dialog

import android.app.Dialog
import androidx.fragment.app.DialogFragment
import com.github.ybq.android.spinkit.sprite.Sprite
import com.github.ybq.android.spinkit.style.FadingCircle
import com.widi.nutechwallet.R
import com.widi.nutechwallet.base.BaseDialogFragment
import kotlinx.android.synthetic.main.loading_default.*

/**
 * Created by widi (widiytk@gmail.com) on 12/02/23.
 **/

class LoadingProgressDialog: BaseDialogFragment() {
    companion object {
        fun newInstance(): LoadingProgressDialog {
            return LoadingProgressDialog()
        }
    }

    override fun setupDialogStyle(dialog: Dialog) {
    }

    override fun loadArguments() {
        setStyle(DialogFragment.STYLE_NORMAL, R.style.DefaultDialog)
    }

    override fun setup() {
        val circle: Sprite = FadingCircle()
        spin_kit.setIndeterminateDrawable(circle)
    }

    override fun getLayout(): Int = R.layout.loading_default
}