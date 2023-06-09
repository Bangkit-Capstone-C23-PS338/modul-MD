package com.example.promosee.view.influencer.mainInflu.ui.home

import androidx.lifecycle.ViewModel
import com.example.promosee.model.repository.CompanyRepository
import com.example.promosee.model.repository.InfluencerRepository

class HomeInfluencerViewModel(
    private val influencerRepository: InfluencerRepository,
) : ViewModel() {

    fun getInfluencrProducts() = influencerRepository.getInfluencerProduct()
    fun getProfile() = influencerRepository.getInfluencer()
    fun getInfluencerOrders(username: String) = influencerRepository.getOrder(username)
}