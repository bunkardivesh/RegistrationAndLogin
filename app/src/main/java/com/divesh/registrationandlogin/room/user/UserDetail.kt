package com.divesh.registrationandlogin.room.user

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_detail")
data class UserDetail(

    @PrimaryKey val userName: String,
    @ColumnInfo(name = "first_name") val fName: String,
    @ColumnInfo(name = "last_name") val lName: String,
    @ColumnInfo(name = "phone") val phone: String,
    @ColumnInfo(name = "dob") val dob: String,
    @ColumnInfo(name = "email") val email: String,
    @ColumnInfo(name = "address") val address: String

)