package com.divesh.registrationandlogin.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.divesh.registrationandlogin.R
import com.divesh.registrationandlogin.databinding.LayoutRegisterBinding
import com.divesh.registrationandlogin.room.user.UserDetail
import com.divesh.registrationandlogin.room.user.UserViewModel
import com.divesh.registrationandlogin.utility.CheckValidation
import java.lang.Exception

class RegistrationFragment: Fragment(R.layout.layout_register) {

    private val viewModel by viewModels<UserViewModel>()

    lateinit var binding: LayoutRegisterBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = LayoutRegisterBinding.bind(view)
        viewModel.init(application = activity?.application!!)
        binding.buttonRegister.setOnClickListener {
            checkFieldsAndRegister()
        }

    }

    private fun checkFieldsAndRegister(){

        val username = binding.registerUsername.editText?.text.toString()
        val email = binding.registerEmail.editText?.text.toString()
        val password = binding.registerPassword.editText?.text.toString()
        val cnfPassword = binding.registerCnfPassword.editText?.text.toString()

        removeError()



        when{
            username.isEmpty() ->  binding.registerUsername.error = "UserName can not be Empty"
            email.isEmpty() -> binding.registerEmail.error = "Email can not be Empty"
            password.isEmpty() ->  binding.registerPassword.error = "Password can not be Empty"
            cnfPassword.isEmpty() -> binding.registerCnfPassword.error = "Field can not be Empty"
            (password != cnfPassword) -> binding.registerCnfPassword.error = "Passwords does not matched!"

            else -> {

                val isEmailValid = CheckValidation.isValidEmail(email)
                val isPasswordValid = CheckValidation.isValidPassword(password)

                if (isEmailValid && isPasswordValid){
                    val user = UserDetail(username,"","","","",email,"")

                    try {
                        val isSaved = viewModel.registerUser(user,password)
                        Toast.makeText(activity,"User Added $isSaved",Toast.LENGTH_SHORT).show()
                    }catch (e: Exception){
                        Toast.makeText(activity,e.message.toString(),Toast.LENGTH_SHORT).show()
                    }
                    defaultFields()
                    activity?.onBackPressed()
                }
                else{
                    when{
                        !isEmailValid -> binding.registerEmail.error = "Please Enter a valid Email"
                        !isPasswordValid -> binding.registerPassword.error = "Password should include alt least one Capital letter, small letter, number and special character"
                    }
                }

            }
        }
    }

    private fun getUserList(){
        viewModel.userList?.observe(viewLifecycleOwner, Observer {
            Toast.makeText(context,it.toString(),Toast.LENGTH_SHORT).show()
        })
    }

    private fun defaultFields(){
        binding.apply {
            registerUsername.editText?.setText("")
            registerEmail.editText?.setText("")
            registerPassword.editText?.setText("")
            registerCnfPassword.editText?.setText("")
        }
        removeError()
    }

    private fun removeError(){
        binding.apply {
            registerUsername.error = null
            registerEmail.error = null
            registerPassword.error = null
            registerCnfPassword.error = null
        }

    }

}