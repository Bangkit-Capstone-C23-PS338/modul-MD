package com.example.promosee.view.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.promosee.model.local.preference.UserModel
import com.example.promosee.model.local.preference.UserPreference
import com.example.promosee.model.repository.AuthRepository

class SplashViewModel(private val pref: UserPreference, private val authRepository: AuthRepository): ViewModel() {
    fun getUser(): LiveData<UserModel> {
        return pref.getUser().asLiveData()
    }

    private lateinit var username : String
    private lateinit var password : String

    fun setInformation(Username : String, Password : String){
        username = Username
        password = Password
    }

    fun generateToken() = authRepository.generateToken(username,password)



}