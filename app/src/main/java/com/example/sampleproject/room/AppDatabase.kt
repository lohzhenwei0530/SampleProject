package com.example.sampleproject.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.sampleproject.Constants
import com.example.sampleproject.room.dao.UserDataDao
import com.example.sampleproject.room.model.UserData

@Database(entities = arrayOf(UserData::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun UserDao(): UserDataDao

    companion object {

        fun getInstance(context: Context): AppDatabase {

            /*Enhancement can be make :
             * 1. Using dagger to provide dependencies
             * 2. Add db migration .*/
            return Room.databaseBuilder(context, AppDatabase::class.java, Constants.Database.DB_NAME)
                .build()
        }
    }
}