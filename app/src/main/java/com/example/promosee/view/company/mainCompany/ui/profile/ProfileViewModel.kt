package com.example.promosee.view.company.mainCompany.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.promosee.model.repository.AuthRepository

class ProfileViewModel(
    private val authRepository: AuthRepository,
) : ViewModel() {

    fun logout() =  authRepository.logout()
}