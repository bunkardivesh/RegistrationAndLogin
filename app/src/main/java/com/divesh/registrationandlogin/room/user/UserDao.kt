package com.divesh.registrationandlogin.room.user

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {

    @Query("Select * From user_detail")
    fun getAllUsers(): LiveData<List<UserDetail>>

    @Insert
    suspend fun insertUser(user: User)

    @Insert
    suspend fun insertUserDetails(user: UserDetail): Long

    @Delete
    suspend fun delete(user: User)

    @Query("Select * From user Where user_name = :username And password = :password")
    fun checkUser(username: String,password: String): LiveData<List<User>>

   /* @Query("Select * From user")
    suspend fun checkUser(): List<User>*/

}