package com.example.sampleproject.room.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

interface BaseDao<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(obj: T): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(obj: List<T>): List<Long>

    @Delete
    fun delete(obj: T)

    @Update
    fun update(obj: T): Int

    @Update
    fun update(obj: List<T>): Int
}