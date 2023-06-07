package com.example.promosee.view.company.mainCompany.ui.detailInfluencer

import androidx.lifecycle.ViewModel
import com.example.promosee.model.repository.CompanyRepository

class InfluencerDetailViewModel(
    private val companyRepository: CompanyRepository
): ViewModel() {

    private lateinit var username : String

    fun setUsername(Username : String){
        username = Username
    }

    fun getInfluencrProducts() =  companyRepository.getInfluencerProduct(username)

    fun getInfluencerProfile() = companyRepository.getInfluencerProfile(username)



}