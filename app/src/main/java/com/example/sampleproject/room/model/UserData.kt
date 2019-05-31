package com.example.sampleproject.room.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class UserData(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
) {
    var userName: String = ""
    var password: String = ""
    var country: String = ""
}
