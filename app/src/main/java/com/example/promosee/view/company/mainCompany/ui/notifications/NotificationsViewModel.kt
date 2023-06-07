package com.example.promosee.view.company.mainCompany.ui.notifications

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.promosee.model.local.preference.UserModel
import com.example.promosee.model.local.preference.UserPreference
import com.example.promosee.model.repository.CompanyRepository

class NotificationsViewModel(private val pref: UserPreference, private val companyRepository: CompanyRepository) : ViewModel() {

    fun getUser(): LiveData<UserModel> = pref.getUser().asLiveData()
    fun getCompanyOrders(username: String) = companyRepository.getOrder(username)
}