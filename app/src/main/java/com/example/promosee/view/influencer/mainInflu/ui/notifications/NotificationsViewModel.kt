package com.example.promosee.view.influencer.mainInflu.ui.notifications

import androidx.lifecycle.ViewModel
import com.example.promosee.model.local.preference.UserPreference
import com.example.promosee.model.repository.InfluencerRepository

class NotificationsViewModel(private val pref: UserPreference, private val influencerRepository: InfluencerRepository) : ViewModel() {
    fun getInfluencerOrders(username: String) = influencerRepository.getOrder(username)
}