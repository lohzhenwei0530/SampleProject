package com.example.sampleproject.network

import android.util.Log
import com.example.sampleproject.Constants
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.jackson.JacksonConverterFactory
import java.util.ArrayList
import java.util.concurrent.TimeUnit

class RestService<S>(val serviceClass: Class<S>) {

    fun createRestService(baseUrl: String) : S{
        val client = OkHttpClient.Builder()
        client.connectTimeout(Constants.Api.CONNECT_TIMEOUT_SEC, TimeUnit.SECONDS)
        client.readTimeout(Constants.Api.READ_TIMEOUT_SEC, TimeUnit.SECONDS)

        val logging = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message ->
            Log.d("Rest Service",message)
        })
        logging.level = HttpLoggingInterceptor.Level.BODY
        client.addInterceptor(logging)

        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(JacksonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client.build())
            .build()
        return retrofit.create(serviceClass)
    }


}