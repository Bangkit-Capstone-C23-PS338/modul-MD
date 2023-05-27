package com.example.promosee.model.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.promosee.R
import com.example.promosee.model.Result
import com.example.promosee.model.local.preference.UserPreference
import com.example.promosee.model.remote.reponse.GetInfluencersResponse
import com.example.promosee.model.remote.retrofit.ApiConfig
import com.example.promosee.model.remote.retrofit.ApiService

class CompanyRepository(
    private val apiService: ApiService, private val pref: UserPreference
) {

    fun getInfluencers(): LiveData<Result<GetInfluencersResponse>> = liveData {
        val token = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJrdWVidW5nYTY5IiwidHlwZSI6ImJ1c2luZXNzX293bmVyIiwianRpIjoiOGNiNjUzY2QxNjEwODA1ZCIsImV4cCI6MTY4NTA5OTg2Nn0.vHwuilaPKCBsN5C0mK2Co66Yu2cER84yZ4fEazk6Njw"
        Log.e("test repo", "sebelum masuk try")
        emit(Result.Loading)
        try{
            val response = apiService.getInfluencers(token)
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


    companion object {
        private val TAG = CompanyRepository::class.java.simpleName

        @Volatile
        private var instance: CompanyRepository? = null
        fun getInstance(
            apiService: ApiService, pref: UserPreference
        ): CompanyRepository = instance ?: synchronized(this) {
            instance ?: CompanyRepository(apiService, pref)
        }.also { instance = it }
    }


}