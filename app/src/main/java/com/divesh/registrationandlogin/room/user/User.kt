package com.divesh.registrationandlogin.room.user

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User (
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "user_name") val userName: String,
    @ColumnInfo(name = "password") val password: String

        )