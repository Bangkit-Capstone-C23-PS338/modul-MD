package com.example.promosee.view.influencer.mainInflu.ui.profile

import androidx.lifecycle.ViewModel
import com.example.promosee.model.repository.AuthRepository
import com.example.promosee.model.repository.InfluencerRepository

class ProfileInfluencerViewModel(
    private val authRepository: AuthRepository,
    private val influencerRepository: InfluencerRepository,
) : ViewModel() {
    fun logout() =  authRepository.logout()

    fun getProfile() = influencerRepository.getInfluencer()

}