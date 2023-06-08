package com.example.promosee.view.influencer.mainInflu.ui.notifications

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.promosee.model.local.preference.UserModel
import com.example.promosee.model.local.preference.UserPreference
import com.example.promosee.model.repository.InfluencerRepository

class NotificationsInfluencerViewModel(private val pref: UserPreference, private val influencerRepository: InfluencerRepository) : ViewModel() {

    fun getUser(): LiveData<UserModel> = pref.getUser().asLiveData()
    fun getInfluencerOrders(username: String) = influencerRepository.getOrder(username)
}