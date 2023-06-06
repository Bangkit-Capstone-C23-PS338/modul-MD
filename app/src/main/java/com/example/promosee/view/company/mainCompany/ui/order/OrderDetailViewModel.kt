package com.example.promosee.view.company.mainCompany.ui.order

import androidx.lifecycle.ViewModel
import com.example.promosee.model.remote.reponse.OrderItem
import com.example.promosee.model.remote.request.UpdateOrderRequest
import com.example.promosee.model.repository.CompanyRepository
import com.example.promosee.model.repository.InfluencerRepository

class OrderDetailViewModel(private val companyRepository: CompanyRepository): ViewModel() {

    fun getOrderDetail(order_id: String) = companyRepository.getOrderDetail(order_id)
    fun updateOrder(update_data: UpdateOrderRequest, order_id: String) =  companyRepository.updateOrder(update_data, order_id)
}