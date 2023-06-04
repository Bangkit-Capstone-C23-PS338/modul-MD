package com.example.promosee.view.company.mainCompany.ui.reviews

import androidx.lifecycle.ViewModel
import com.example.promosee.model.repository.CompanyRepository
import com.example.promosee.model.repository.InfluencerRepository

class ReviewsViewModel(
    private val companyRepository: CompanyRepository
    ): ViewModel() {

    private lateinit var username : String

    fun setUsername(Username : String){
        username = Username
    }

    fun getReviews() =  companyRepository.getReviews(username)

}