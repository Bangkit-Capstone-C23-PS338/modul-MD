package com.example.promosee.view.company.mainCompany.ui.profile

import androidx.lifecycle.ViewModel
import com.example.promosee.model.repository.AuthRepository
import com.example.promosee.model.repository.CompanyRepository

class ProfileViewModel(
    private val authRepository: AuthRepository, private val companyRepository: CompanyRepository
) : ViewModel() {

    fun logout() =  authRepository.logout()

    fun getProfile() = companyRepository.getCompanyProfile()
}