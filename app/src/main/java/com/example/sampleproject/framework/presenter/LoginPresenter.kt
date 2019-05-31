package com.example.sampleproject.framework.presenter

import android.content.Context
import android.util.Log
import com.example.sampleproject.framework.base.BasePresenter
import com.example.sampleproject.framework.view.LoginView
import com.example.sampleproject.room.DatabaseHelper
import com.example.sampleproject.room.model.UserData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class LoginPresenter constructor(view: LoginView, context: Context) : BasePresenter<LoginView>() {

    val TAG = "LoginPresenter"
    val databaseHelper: DatabaseHelper

    init {
        bindView(view)
        databaseHelper = DatabaseHelper(context)
    }

    fun getUser(id: String, pass: String, country: String) {
        databaseHelper.getUser(id, pass, country)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                    Log.d(TAG, "Log in old user")
                    getView()?.onLoginSuccess()
                },
                onError = {
                    /*No such user , saving user to db automatically.*/
                    val user = UserData()
                    user.userName = id
                    user.password = pass
                    user.country = country
                    registerNewUser(user)
                }
            )
    }

    fun registerNewUser(user: UserData) {
        databaseHelper.inserUser(user)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onNext = {
                    Log.d(TAG, "Save a new user")
                    getView()?.onLoginSuccess()
                    getAlluser()
                },
                onError = {
                    Log.d(TAG, it.toString())
                    getView()?.onError(it.toString())
                }
            )
    }

    /*For testing ensure data insert to db.*/
    fun getAlluser() {
        databaseHelper.getAllUser()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                    Log.d(TAG, it.size.toString())
                },
                onError = {
                    Log.d(TAG, it.toString())
                }
            )
    }

}