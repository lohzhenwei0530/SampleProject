package com.example.sampleproject.network

import com.example.sampleproject.Constants
import com.example.sampleproject.network.model.User
import io.reactivex.Observable
import retrofit2.http.GET

interface ServerService {

    @GET(Constants.Api.Url.users)
    fun getListUser() : Observable<List<User>>

}