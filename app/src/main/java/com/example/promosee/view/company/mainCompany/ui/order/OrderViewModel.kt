package com.example.promosee.view.company.mainCompany.ui.order

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.example.promosee.model.local.preference.OrderModel
import com.example.promosee.model.local.preference.UserModel
import com.example.promosee.model.local.preference.UserPreference
import com.example.promosee.model.repository.CompanyRepository

class OrderViewModel(private val pref: UserPreference, private val companyRepository: CompanyRepository) {
    fun getUser(): LiveData<UserModel> = pref.getUser().asLiveData()

    fun createOrder(order: OrderModel, username: String) =  companyRepository.createOrder(order, username)
}