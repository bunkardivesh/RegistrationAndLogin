package com.divesh.registrationandlogin.room.user

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class UserViewModel: ViewModel() {

    var userList: LiveData<List<UserDetail>>? = null

    lateinit var repository: UserRepository


    fun init(application: Application) {
        val dao = AppDatabase.getDatabase(application).getUserDao()
        repository = UserRepository(dao)
        this.userList = repository.userList
    }


    fun checkUser(username: String,password: String): LiveData<List<User>>?{
       var list : LiveData<List<User>>? = null
        viewModelScope.launch {
            list = repository.checkUser(username,password)
            Log.i("viewModel:",list.toString())
        }
       return list
    }

    fun registerUser(userDetail: UserDetail,password: String): Long{
        var result: Long = 0
    viewModelScope.launch {
        result = repository.insertUserDetails(userDetail)
        repository.insertUser(User(1,userDetail.userName,password))
    }
      return result
    }
}