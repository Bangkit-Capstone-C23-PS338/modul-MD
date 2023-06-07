package com.example.promosee.view.company.mainCompany.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.promosee.model.repository.CompanyRepository

class SearchViewModel(private val companyRepository: CompanyRepository) : ViewModel() {

    fun getInfluencrs() =  companyRepository.getInfluencers()

    private lateinit var username : String

    fun setUsername(Username : String){
        username = Username
    }
    fun getInfluencersSearch() = companyRepository.getInfluencerSearch(username)

}