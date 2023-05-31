package com.example.promosee.view.company.mainCompany.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.promosee.model.local.preference.UserModel
import com.example.promosee.model.local.preference.UserPreference
import com.example.promosee.model.repository.CompanyRepository

class HomeViewModel(private val pref: UserPreference, private val companyRepository: CompanyRepository) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    fun getUser(): LiveData<UserModel> {
        val user =  pref.getUser().asLiveData()
        _text.value = user.value?.username
        return user
    }

    fun getInfluencers() =  companyRepository.getInfluencers()

}