package com.example.sampleproject.framework.presenter

import com.example.sampleproject.framework.base.BasePresenter
import com.example.sampleproject.framework.view.GoogleMapView
import com.example.sampleproject.framework.view.LoginView

class GoogleMapPresenter constructor(view : GoogleMapView) : BasePresenter<GoogleMapView>() {

    init {
        bindView(view)
    }
}