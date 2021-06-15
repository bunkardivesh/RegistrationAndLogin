package com.divesh.registrationandlogin.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.divesh.registrationandlogin.LoginActivity
import com.divesh.registrationandlogin.MainActivity
import com.divesh.registrationandlogin.R
import com.divesh.registrationandlogin.databinding.LayoutLoginBinding
import com.divesh.registrationandlogin.room.user.UserViewModel
import com.google.android.material.snackbar.Snackbar

class LoginFragment: Fragment(R.layout.layout_login) {

    private val viewModel by viewModels<UserViewModel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = LayoutLoginBinding.bind(view)

        viewModel.init(activity?.application!!)

        binding.btnToRegister.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToRegistrationFragment()
            findNavController().navigate(action)
         }


        binding.buttonLogin.setOnClickListener {
            checkUser(binding)
        }

    }

    private fun checkUser(binding: LayoutLoginBinding){

        val username = binding.inputUsername.editText?.text.toString()
        val password = binding.inputPassword.editText?.text.toString()

        try {

            if (username.isNotEmpty() && password.isNotEmpty()){
                viewModel.checkUser(username,password)?.observe(viewLifecycleOwner,
                    Observer {userList ->

                        if (userList != null){
                            if (userList!!.isNotEmpty()){
                                Log.i("LoginFragment: ",userList.toString())
                                (activity as LoginActivity).toMainActivity()
                            }else{
                                Snackbar.make(requireView(),"User Not Registered! or check Credentials",Snackbar.LENGTH_SHORT).show()
                                Log.i("LoginFragment: ",userList.toString())
                            }
                        }else
                            Log.i("LoginFragment: ","null")
                    })

            }else{
                binding.inputUsername.error = "Please Enter UserName!"
                binding.inputPassword.error = "Please Enter Password!"
            }

        }catch (exception:Exception){
            Snackbar.make(requireView(),exception.message.toString(),Snackbar.LENGTH_SHORT).show()
        }

    }

}