package com.example.sampleproject.framework.presenter

import android.util.Log
import com.example.sampleproject.Constants
import com.example.sampleproject.framework.base.BasePresenter
import com.example.sampleproject.framework.view.UserView
import com.example.sampleproject.network.RestService
import com.example.sampleproject.network.ServerService
import com.example.sampleproject.network.model.User
import io.reactivex.Observer
import io.reactivex.schedulers.Schedulers
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable

class UserPresenter constructor(view: UserView) : BasePresenter<UserView>() {

    val TAG = "UserPresenter"
    var restService: ServerService

    init {
        bindView(view)
        restService =
            RestService(ServerService::class.java).createRestService(Constants.Api.BaseUrl.jsonBase)
    }

    fun getUser() {
        restService.getListUser()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<List<User>>{
                override fun onComplete() {
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(user: List<User>) {
                   getView()?.onGetUserSuccess(user)
                }

                override fun onError(e: Throwable) {
                    Log.d(TAG,"$e")
                    getView()?.onError(e.toString())
                }
         })
    }
}