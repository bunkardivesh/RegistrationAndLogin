package com.divesh.registrationandlogin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.divesh.registrationandlogin.R

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


    }
     public fun toMainActivity(){
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }

}