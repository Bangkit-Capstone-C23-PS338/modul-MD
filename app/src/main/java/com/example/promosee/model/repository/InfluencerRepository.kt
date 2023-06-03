package com.example.promosee.model.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.promosee.model.Result
import com.example.promosee.model.local.preference.UserPreference
import com.example.promosee.model.remote.reponse.GetInfluencerProductReponse
import com.example.promosee.model.remote.reponse.GetInfluencersResponse
import com.example.promosee.model.remote.reponse.getInfleuncerProfileResponse
import com.example.promosee.model.remote.retrofit.ApiConfig
import com.example.promosee.model.remote.retrofit.ApiService

class InfluencerRepository(
    private val apiService: ApiService,
    private val pref: UserPreference
) {

    fun getInfluencer(): LiveData<Result<getInfleuncerProfileResponse>> = liveData {
        val token = "Bearer ${ApiConfig.TOKEN}"
        val username = ApiConfig.USERNAME
        emit(Result.Loading)
        try{
            val response = apiService.getInfluencerProfile(token,username)
            Log.e("test repo", "setelah masuk try")
            if(response == null){
                emit(Result.Error("Failed to fetch influencer data "))
            }else{
                Log.e("test repo", "data masuk")
                emit(Result.Success(response))
            }
        }catch (e : Exception){
            Log.d("CompanyRepository", "findUser: ${e.message.toString()}")
            val message = e.message.toString()
            if (message == "") {
                emit(Result.Error("Whoops, Something went wrong"))
            } else {
                emit(Result.Error(message))
            }
        }
    }

    fun getInfluencerProduct(): LiveData<Result<GetInfluencerProductReponse>> = liveData {
        val token = "Bearer ${ApiConfig.TOKEN}"
        val username = ApiConfig.USERNAME
        emit(Result.Loading)
        try{
            val response = apiService.getInfluencerProducts(token,username)
            if(response == null){
                emit(Result.Error("Failed to fetch influencer products "))
            }else{
                Log.e("test product", "data masuk")
                emit(Result.Success(response))
            }
        }catch (e : Exception){
            Log.d("CompanyRepository", "findUser: ${e.message.toString()}")
            val message = e.message.toString()
            if (message == "") {
                emit(Result.Error("Whoops, Something went wrong"))
            } else {
                emit(Result.Error(message))
            }
        }
    }


    companion object {
        private val TAG = InfluencerRepository::class.java.simpleName

        @Volatile
        private var instance: InfluencerRepository? = null
        fun getInstance(
            apiService: ApiService, pref: UserPreference
        ): InfluencerRepository = instance ?: synchronized(this) {
            instance ?: InfluencerRepository(apiService, pref)
        }.also { instance = it }
    }

}