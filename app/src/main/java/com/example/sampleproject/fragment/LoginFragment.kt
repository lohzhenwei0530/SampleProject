package com.example.sampleproject.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.SpinnerAdapter
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.sampleproject.R
import com.example.sampleproject.framework.base.BaseFragment
import com.example.sampleproject.framework.presenter.LoginPresenter
import com.example.sampleproject.framework.view.LoginView
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment(), LoginView{

    lateinit var presenter : LoginPresenter

    override fun onStart() {
        super.onStart()
        presenter = LoginPresenter(this)
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

    private fun initView(){
        var sampleCountry = arrayOf("Malaysia","Singapore","Thailand","Vietnam")
        sp_country.adapter =  ArrayAdapter<String>(context!!, android.R.layout.simple_spinner_dropdown_item,sampleCountry)

        btn_next.setOnClickListener{
            if(validateField()){
                findNavController().navigate(R.id.action_to_main_activity)
                activity?.finish()
            }

        }
    }

    private fun validateField() : Boolean{
        /*Add Validation rule here.*/
      /*  if (et_name.text.isEmpty()){
            return false
        }
        if (et_password.text.isEmpty()){
            return false
        }*/
        return true
    }

    override fun onError(message: String) {

    }
}