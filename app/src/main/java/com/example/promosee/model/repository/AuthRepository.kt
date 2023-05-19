package com.example.promosee.model.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.promosee.model.Result
import com.example.promosee.model.local.preference.UserModel
import com.example.promosee.model.local.preference.UserPreference
import com.example.promosee.model.remote.reponse.LoginResponse
import com.example.promosee.model.remote.retrofit.ApiConfig
import com.example.promosee.model.remote.retrofit.ApiService

class AuthRepository(
    private val apiService: ApiService, private val pref: UserPreference
) {

    fun login(username: String, password: String) : LiveData<Result<LoginResponse>> = liveData {
        emit(Result.Loading)
        try{
            val response = apiService.userLogin(username,password)
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