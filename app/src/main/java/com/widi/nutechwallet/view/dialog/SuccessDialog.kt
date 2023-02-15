package com.widi.nutechwallet.view.dialog

import android.app.Dialog
import android.content.Intent
import androidx.core.content.ContextCompat
import com.widi.nutechwallet.R
import com.widi.nutechwallet.base.BaseDialogFragment
import com.widi.nutechwallet.objects.Params
import com.widi.nutechwallet.view.home.HomeActivity
import kotlinx.android.synthetic.main.success_dialog.*

/**
 * Created by widi (widiytk@gmail.com) on 15/02/23.
 **/

class SuccessDialog: BaseDialogFragment() {

    private var text: String? = null

    companion object {
        fun newInstance(): SuccessDialog {
            return SuccessDialog()
        }
    }

    override fun setupDialogStyle(dialog: Dialog) {
        activity?.apply {
            dialog.window?.statusBarColor = ContextCompat.getColor(applicationContext, R.color.transparent_white)
        }
    }

    override fun loadArguments() {
        setStyle(STYLE_NORMAL, R.style.DefaultDialog_Fullscreen_Transparent)
        arguments?.let {
            text = it.getString(Params.BUNDLE_TEXT)
        }
    }

    override fun setup() {
        initAction()
    }

    override fun getLayout(): Int = R.layout.success_dialog

    private fun initAction() {
        btnDismissPopup.setOnClickListener {
            dismiss()
            onClick()
        }
    }

    private fun onClick() {
        val intent = Intent(context, HomeActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        context?.startActivity(intent)
    }
}