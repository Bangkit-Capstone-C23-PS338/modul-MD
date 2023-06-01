package com.example.promosee.model.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.promosee.model.Result
import com.example.promosee.model.local.preference.CompanyModel
import com.example.promosee.model.local.preference.InfluencerModel
import com.example.promosee.model.local.preference.UserModel
import com.example.promosee.model.local.preference.UserPreference
import com.example.promosee.model.remote.reponse.LoginResponse
import com.example.promosee.model.remote.reponse.LogoutResponse
import com.example.promosee.model.remote.reponse.RegisterResponse
import com.example.promosee.model.remote.request.User
import com.example.promosee.model.remote.retrofit.ApiConfig
import com.example.promosee.model.remote.retrofit.ApiService

class AuthRepository(
    private val apiService: ApiService, private val pref: UserPreference
) {
    fun login(username: String, password: String) : LiveData<Result<LoginResponse>> = liveData {
        emit(Result.Loading)
        try{
            val user = User(username = username, password)
            val response = apiService.userLogin(user)
            if(response == null){
                emit(Result.Error("Login gagal"))
            }else{
                // instance usermodel
                val userInfo = UserModel(
                    username = response.username ?: "",
                    access_token = response.accessToken ?: "",
                    userid = response.userid ?: ""
                )
                // menyimpan data ke data-store
                pref.saveUser(userInfo)
                ApiConfig.TOKEN = response.accessToken ?: ""
                emit(Result.Success(response))
            }
        }catch (e : Exception){
            Log.e("AuthRepository", "findUser: ${e.message.toString()}")
            val message = e.message.toString()
            if (message == "") {
                emit(Result.Error("Whoops, Something went wrong"))
            } else {
                emit(Result.Error(message))
            }
        }
    }

    fun registerInfluencer(influencer: InfluencerModel) : LiveData<Result<RegisterResponse>> = liveData {
        emit(Result.Loading)
        try{
            val response = apiService.influencerRegister(influencer)
            if(response == null){
                emit(Result.Error("Register gagal"))
            }else{
                Log.d("Cek register influencer", response.message)
                emit(Result.Success(response))
            }
        }catch (e : Exception){
            Log.d("AuthRepository", "findUser: ${e.message.toString()}")
            val message = e.message.toString()
            if (message == "") {
                emit(Result.Error("Whoops, Something went wrong"))
            } else {
                emit(Result.Error(message))
            }
        }
    }

    fun registerCompany(company: CompanyModel) : LiveData<Result<RegisterResponse>> = liveData {
        emit(Result.Loading)
        try{
            val response = apiService.companyRegister(company)
            if(response == null){
                emit(Result.Error("Register gagal"))
            }else{
                emit(Result.Success(response))
            }
        }catch (e : Exception){
            Log.d("AuthRepository", "findUser: ${e.message.toString()}")
            val message = e.message.toString()
            if (message == "") {
                emit(Result.Error("Whoops, Something went wrong"))
            } else {
                emit(Result.Error(message))
            }
        }
    }

    fun logout() : LiveData<Result<LogoutResponse>> = liveData {
        emit(Result.Loading)
        val token = "Bearer ${ApiConfig.TOKEN}"
        try{
            val response = apiService.userLogout(token)
            if(response == null){
                emit(Result.Error("logout fail"))
            }else{
                pref.removeUser()
                emit(Result.Success(response))
            }
        }catch (e : Exception){
            Log.d("AuthRepository", "findUser: ${e.message.toString()}")
            val message = e.message.toString()
            if (message == "") {
                emit(Result.Error("Whoops, Something went wrong"))
            } else {
                emit(Result.Error(message))
            }
        }
    }

    companion object {
        private val TAG = AuthRepository::class.java.simpleName

        @Volatile
        private var instance: AuthRepository? = null
        fun getInstance(
            apiService: ApiService, pref: UserPreference
        ): AuthRepository = instance ?: synchronized(this) {
            instance ?: AuthRepository(apiService, pref)
        }.also { instance = it }
    }

}