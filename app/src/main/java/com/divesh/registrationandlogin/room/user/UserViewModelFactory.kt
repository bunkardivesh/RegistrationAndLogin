package com.divesh.registrationandlogin.room.user

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

open class UserViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModelFactory::class.java)) {
            return UserViewModelFactory(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}