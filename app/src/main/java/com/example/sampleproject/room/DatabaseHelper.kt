package com.example.sampleproject.room

import android.content.Context
import com.example.sampleproject.room.model.UserData
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single

class DatabaseHelper(context: Context) {

    val appDatabase = AppDatabase.getInstance(context)

    fun getUser(id: String, pass: String, country: String): Single<UserData> {
        return appDatabase.UserDao().getUser(id, pass, country)
    }

    fun inserUser(user: UserData): Observable<Long> {
        return Observable.create {
            it.onNext(appDatabase.UserDao().insert(user))
            it.onComplete()
        }
    }

    fun getAllUser(): Maybe<List<UserData>> {
        return appDatabase.UserDao().getAll()
    }
}