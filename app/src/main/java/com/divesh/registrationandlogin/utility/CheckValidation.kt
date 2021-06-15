package com.divesh.registrationandlogin.utility

import android.text.TextUtils
import android.util.Patterns
import java.util.regex.Pattern

class CheckValidation {


    companion object{

        private val PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$"


        fun isValidEmail(email: String): Boolean {
            return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()
        }

        fun isValidPassword(password: String): Boolean{
            return !TextUtils.isEmpty(password) && Pattern.compile(PASSWORD_PATTERN).matcher(password).matches()
        }
    }

}