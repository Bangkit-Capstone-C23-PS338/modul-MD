package com.example.promosee.view.company.mainCompany.ui.notifications

import androidx.lifecycle.ViewModel
import com.example.promosee.model.local.preference.UserPreference
import com.example.promosee.model.repository.CompanyRepository

class NotificationsViewModel(private val pref: UserPreference, private val companyRepository: CompanyRepository) : ViewModel() {
    fun getCompanyOrders(username: String) = companyRepository.getOrder(username)
}