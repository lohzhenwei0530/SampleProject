package com.example.sampleproject.util

import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog
import com.example.sampleproject.R

class DialogUtil {

    companion object {
        fun showOneButtonDialog(
            context: Context?,
            message: String?,
            positiveButton: String?,
            onClickListener: DialogInterface.OnClickListener? = null
        ) {
            val builder: AlertDialog.Builder? = context?.let {
                AlertDialog.Builder(it, R.style.CustomDialog)
            }
            builder?.setMessage(message)
                ?.setPositiveButton(positiveButton, onClickListener)
                ?.setCancelable(false)

            val dialog: AlertDialog? = builder?.create()
            dialog?.setCanceledOnTouchOutside(false)
            dialog?.show()
        }

    }
}