package com.divesh.registrationandlogin.room.user

import android.util.Log
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserRepository(private val userDao: UserDao) {

    val userList: LiveData<List<UserDetail>> = userDao.getAllUsers()

    suspend fun insertUser(user: User) {
        userDao.insertUser(user)
    }

    suspend fun deleteUser(user: User) {
        userDao.delete(user)
    }

    suspend fun insertUserDetails(user: UserDetail): Long {
       return userDao.insertUserDetails(user)
    }

    fun checkUser(username: String,password: String): LiveData<List<User>>
    {
        return userDao.checkUser(username,password)!!

       /* var newList: List<User>
        var list = userDao.checkUser()
        Log.i("printUser:",list.toString())
        for (user in list){
            if (user.userName == username && user.password == password){
                Log.i("printUser:",user.toString())
            }
        }*/
    }
}