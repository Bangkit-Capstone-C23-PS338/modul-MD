package com.example.promosee.view.login

import androidx.lifecycle.ViewModel
import com.example.promosee.model.repository.AuthRepository

class LoginViewModel(private val authRepository: AuthRepository): ViewModel() {

    private lateinit var username : String
    private lateinit var password : String

    fun setLoginInfo(Username : String, Password : String){
        username = Username
        password = Password
    }

    fun login() = authRepository.login(username,password)

}