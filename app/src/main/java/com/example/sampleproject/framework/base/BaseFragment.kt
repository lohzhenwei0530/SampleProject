package com.example.sampleproject.framework.base

import android.content.DialogInterface
import androidx.fragment.app.Fragment
import com.example.sampleproject.activity.MainActivity
import com.example.sampleproject.util.DialogUtil

abstract class BaseFragment : Fragment(), BaseView {

    protected fun getMainActivity(): MainActivity? {
        var mainActivity: MainActivity? = null
        activity?.apply {
            mainActivity = this as MainActivity
        }
        return mainActivity
    }

    protected fun setTitle(title: String) {
        getMainActivity()?.setToolBarTitle(title)
    }

    protected fun showLoadingProgress() {
        getMainActivity()?.showLoadingProgress()
    }

    protected fun hideLoadingProgress() {
        getMainActivity()?.hideLoadingProgress()
    }

    override fun onError(message: String) {
        hideLoadingProgress()
        DialogUtil.showOneButtonDialog(activity,
            message,
            "Okay",
            DialogInterface.OnClickListener { dialog, _ ->
                dialog.dismiss()
            })
    }
}