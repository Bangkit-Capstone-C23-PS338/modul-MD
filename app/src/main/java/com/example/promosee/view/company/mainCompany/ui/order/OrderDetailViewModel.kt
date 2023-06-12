package com.example.promosee.view.company.mainCompany.ui.order

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.promosee.model.local.preference.UserModel
import com.example.promosee.model.local.preference.UserPreference
import com.example.promosee.model.remote.request.UpdateOrderRequest
import com.example.promosee.model.repository.CompanyRepository

class OrderDetailViewModel(private val pref: UserPreference, private val companyRepository: CompanyRepository): ViewModel() {

    fun getUser(): LiveData<UserModel> = pref.getUser().asLiveData()
    fun getOrderDetail(order_id: String) = companyRepository.getOrderDetail(order_id)
    fun updateOrder(update_data: UpdateOrderRequest, order_id: String) =  companyRepository.updateOrder(update_data, order_id)
}