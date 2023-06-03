package com.example.promosee.injection

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.promosee.model.local.preference.UserPreference
import com.example.promosee.model.remote.retrofit.ApiConfig
import com.example.promosee.model.repository.AuthRepository
import com.example.promosee.model.repository.CompanyRepository
import com.example.promosee.model.repository.InfluencerRepository

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

object Injection {

    fun authRepository(context : Context): AuthRepository {
        val apiService = ApiConfig.getApiService()
        val userPreference = UserPreference.getInstance(context.dataStore)
        return AuthRepository.getInstance(apiService,userPreference)
    }
    fun companyRepository(context: Context): CompanyRepository{
        val apiService = ApiConfig.getApiService()
        val userPreference = UserPreference.getInstance(context.dataStore)
        return CompanyRepository.getInstance(apiService,userPreference)
    }

    fun influencerRepository(context: Context): InfluencerRepository{
        val apiService = ApiConfig.getApiService()
        val userPreference = UserPreference.getInstance(context.dataStore)
        return InfluencerRepository.getInstance(apiService,userPreference)
    }
    fun providePreferences(context: Context): UserPreference {
        return UserPreference.getInstance(context.dataStore)
    }

}