package com.example.sampleproject.framework.presenter

import com.example.sampleproject.framework.base.BasePresenter
import com.example.sampleproject.framework.view.LoginView

class LoginPresenter constructor(view : LoginView) : BasePresenter<LoginView>() {

    init {
        bindView(view)
    }
}