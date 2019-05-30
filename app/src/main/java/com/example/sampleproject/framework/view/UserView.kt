package com.example.sampleproject.framework.view

import com.example.sampleproject.framework.base.BaseView
import com.example.sampleproject.network.model.User

interface UserView  : BaseView{

    fun onGetUserSuccess(user : List<User>)
}