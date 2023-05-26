package com.example.promosee.view.company.mainCompany.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.promosee.model.repository.CompanyRepository

class SearchViewModel(private val companyRepository: CompanyRepository) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }
    val text: LiveData<String> = _text

    fun getInfluencrs() =  companyRepository.getInfluencers()
}