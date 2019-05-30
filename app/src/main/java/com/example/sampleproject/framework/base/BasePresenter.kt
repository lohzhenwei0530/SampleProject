package com.example.sampleproject.framework.base

import android.view.View
import java.lang.ref.WeakReference

open class BasePresenter<V : BaseView> {

    lateinit var view : WeakReference<V>

    fun bindView(view : V){
        this.view= WeakReference(view)
    }

    fun unbindView(){
        view.clear()
    }

    fun getView() : V?{
        return view.get()
    }
}