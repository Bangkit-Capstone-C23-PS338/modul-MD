package com.example.promosee.model.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.promosee.R
import com.example.promosee.model.Result
import com.example.promosee.model.local.preference.UserPreference
import com.example.promosee.model.remote.reponse.GetInfluencerProductReponse
import com.example.promosee.model.remote.reponse.GetInfluencersResponse
import com.example.promosee.model.remote.retrofit.ApiConfig
import com.example.promosee.model.remote.retrofit.ApiService

class CompanyRepository(
    private val apiService: ApiService, private val pref: UserPreference
) {

    // mengambil data untuk ditampilkan pada GRID list
    fun getInfluencers(): LiveData<Result<GetInfluencersResponse>> = liveData {
        val token = "Bearer ${ApiConfig.TOKEN}"
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

    // menampilkan data untuk melihat daftar produk dari influencer yang dipilih
    fun getInfluencerProduct(username: String): LiveData<Result<GetInfluencerProductReponse>> = liveData {
        val token = "Bearer ${ApiConfig.TOKEN}"
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