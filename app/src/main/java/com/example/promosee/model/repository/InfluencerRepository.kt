package com.example.promosee.model.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.promosee.model.Result
import com.example.promosee.model.local.preference.UserPreference
import com.example.promosee.model.remote.reponse.GetInfluencerProductReponse
import com.example.promosee.model.remote.reponse.GetInfluencersResponse
import com.example.promosee.model.remote.reponse.GetOrderResponse
import com.example.promosee.model.remote.reponse.OrderItem
import com.example.promosee.model.remote.reponse.PostProductResponse
import com.example.promosee.model.remote.reponse.PostRes
import com.example.promosee.model.remote.reponse.deleteProductResponse
import com.example.promosee.model.remote.reponse.getInfleuncerProfileResponse
import com.example.promosee.model.remote.reponse.getProductItemResponse
import com.example.promosee.model.remote.request.PostProductRequest
import com.example.promosee.model.remote.request.UpdateOrderRequest
import com.example.promosee.model.remote.request.UpdateProductRequest
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

    fun postInfluencerProduct(
        socialMediaType: String,
        price: String,
        name: String,
        description: String,
        todo: String
    ): LiveData<Result<PostRes>> = liveData {
        emit(Result.Loading)
        try{
            val token = "Bearer ${ApiConfig.TOKEN}"
            val username = ApiConfig.USERNAME
            val influencerProduct = PostProductRequest(
                socialMediaType = socialMediaType,
                price = price.toInt(),
                name = name,
                description = description,
                toDo = convertStringToList(todo),
                postingDate = "2023-06-04T10:30:15.123+0530"
            )
            val response = apiService.createProduct(token,influencerProduct,username)
            if(response == null){
                emit(Result.Error("Failed to fetch influencer products "))
            }else{
                Log.e("test product", "data masuk")
                Log.e("test product respon", response.message.toString())
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

    fun getProductItem(id: String): LiveData<Result<getProductItemResponse>> = liveData {
        val token = "Bearer ${ApiConfig.TOKEN}"
        val username = ApiConfig.USERNAME
        emit(Result.Loading)
        try{
            val response = apiService.getProductItem(token,username,id)
            if(response == null){
                emit(Result.Error("Failed to fetch product item "))
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

    fun updateInfluencerProduct(
        socialMediaType: String,
        price: String,
        name: String,
        description: String,
        todo: String,
        id: String
    ): LiveData<Result<UpdateProductRequest>> = liveData {
        emit(Result.Loading)
        try{
            val token = "Bearer ${ApiConfig.TOKEN}"
            val username = ApiConfig.USERNAME
            val influencerProduct = UpdateProductRequest(
                socialMediaType = socialMediaType,
                price = price.toInt(),
                name = name,
                description = description,
                toDo = convertStringToList(todo),
            )
            val response = apiService.UpdateProductItem(token,influencerProduct,username,id)
            if(response == null){
                emit(Result.Error("Failed to fetch influencer products "))
            }else{
                Log.e("test product", "data masuk")
                Log.e("test product respon", "sup!")
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

    fun deleteProductitem(id: String): LiveData<Result<deleteProductResponse>> = liveData {
        val token = "Bearer ${ApiConfig.TOKEN}"
        val username = ApiConfig.USERNAME
        emit(Result.Loading)
        try{
            val response = apiService.deleteProductItem(token,username,id)
            if(response == null){
                emit(Result.Error("Failed to delete product item "))
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

    fun getOrder(username: String) : LiveData<Result<GetOrderResponse>> = liveData {
        emit(Result.Loading)
        val token = "Bearer ${ApiConfig.TOKEN}"
        try {
            val response = apiService.getInfluencerOrders(token, username)
            if (response == null) {
                emit(Result.Error("Order gagal"))
            } else {
                Log.d("Cek Order", response.orders.toString())
                emit(Result.Success(response))
            }
        } catch (e: Exception) {
            Log.d("CompanyRepository", " ${e.message.toString()}")
            val message = e.message.toString()
            if (message == "") {
                emit(Result.Error("Whoops, Something went wrong"))
            } else {
                emit(Result.Error(message))
            }
        }
    }

    fun updateOrder(update_data: UpdateOrderRequest, order_id: String) : LiveData<Result<PostRes>> = liveData {
        emit(Result.Loading)
        val token = "Bearer ${ApiConfig.TOKEN}"
        try {
            val response = apiService.updateOrder(token, update_data, order_id)
            if (response == null) {
                emit(Result.Error("Order gagal"))
            } else {
                Log.d("Cek Order", response.message.toString())
                emit(Result.Success(response))
            }
        } catch (e: Exception) {
            Log.d("CompanyRepository", " ${e.message.toString()}")
            val message = e.message.toString()
            if (message == "") {
                emit(Result.Error("Whoops, Something went wrong"))
            } else {
                emit(Result.Error(message))
            }
        }
    }

    fun convertStringToList(input: String): List<String> {
        val list = input.split(",").map { it.trim() }
        return list
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