package com.example.promosee.view.company.mainCompany.ui.order

import androidx.lifecycle.ViewModel
import com.example.promosee.model.remote.reponse.OrderItem
import com.example.promosee.model.repository.CompanyRepository
import com.example.promosee.model.repository.InfluencerRepository

class OrderDetailViewModel(private val companyRepository: CompanyRepository, private val influencerRepository: InfluencerRepository): ViewModel() {

    fun createOrder(order: OrderItem, username: String) =  companyRepository.createOrder(order, username)
}