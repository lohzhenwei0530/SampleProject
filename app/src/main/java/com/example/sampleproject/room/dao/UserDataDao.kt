package com.example.sampleproject.room.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.sampleproject.room.model.UserData
import io.reactivex.Maybe
import io.reactivex.Single

@Dao
interface UserDataDao : BaseDao<UserData> {

    @Query("DELETE FROM UserData")
    fun deleteTable()

    @Query("SELECT * FROM UserData")
    fun getAll(): Maybe<List<UserData>>

    @Query ("SELECT * FROM  UserData WHERE userName ==(:name) AND password == (:pass) AND country ==(:country)")
    fun getUser (name : String , pass : String, country : String) : Single<UserData>
}