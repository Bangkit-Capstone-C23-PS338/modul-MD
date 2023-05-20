package com.example.promosee.view.register

import androidx.lifecycle.ViewModel
import com.example.promosee.model.local.preference.CompanyModel
import com.example.promosee.model.local.preference.InfluencerModel
import com.example.promosee.model.repository.AuthRepository

class RegisterViewModel(private val authRepository: AuthRepository): ViewModel() {

    private lateinit var influencer : InfluencerModel
    private lateinit var company : CompanyModel

    fun setInfluencer(newInfluencer: InfluencerModel){
        influencer = newInfluencer
    }
    fun setCompany(newCompany: CompanyModel){
        company = newCompany
    }

    fun registerInfluencer() = authRepository.registerInfluencer(influencer)
    fun registerCompany() = authRepository.registerCompany(company)

}