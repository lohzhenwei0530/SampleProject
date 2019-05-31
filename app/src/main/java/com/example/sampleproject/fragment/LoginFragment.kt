package com.example.sampleproject.fragment

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.sampleproject.R
import com.example.sampleproject.framework.presenter.LoginPresenter
import com.example.sampleproject.framework.view.LoginView
import com.example.sampleproject.util.DialogUtil
import kotlinx.android.synthetic.main.fragment_login.*

/* Things for improvement :
 * Create a page for user to sign up .
 * Use server (e.g:firebase) instead of local db
 * Saving login info into share preference.*/
class LoginFragment : Fragment(), LoginView {

    lateinit var presenter: LoginPresenter

    override fun onStart() {
        super.onStart()
        presenter = LoginPresenter(this, context!!)
        presenter.bindView(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        activity?.title = "Login"
    }

    override fun onStop() {
        super.onStop()
        presenter.unbindView()
    }

    override fun onLoginSuccess() {
        findNavController().navigate(R.id.action_to_main_activity)
        activity?.finish()
    }

    private fun initView() {
        var sampleCountry = arrayOf("Malaysia", "Singapore", "Thailand", "Vietnam")
        sp_country.adapter =
            ArrayAdapter<String>(context!!, android.R.layout.simple_spinner_dropdown_item, sampleCountry)

        btn_next.setOnClickListener {
            if (validateField()) {
                tv_password_error.visibility = View.GONE
                tv_username_error.visibility = View.GONE

                /*Just an example that get user from room db only.*/
                presenter.getUser(
                    et_name.text.toString(),
                    et_password.text.toString(),
                    sp_country.selectedItem.toString()
                )
            }
        }
    }

    private fun validateField(): Boolean {
        /*Sample validation.*/
        var isValid = true
        if (et_name.text.isEmpty()) {
            isValid = false
            tv_password_error.visibility = View.VISIBLE
        }
        if (et_password.text.isEmpty()) {
            isValid = false
            tv_username_error.visibility = View.VISIBLE
        }
        return isValid
    }

    override fun onError(message: String) {
        DialogUtil.showOneButtonDialog(activity,
            message,
            "Okay",
            DialogInterface.OnClickListener { dialog, _ ->
                dialog.dismiss()
            })
    }
}