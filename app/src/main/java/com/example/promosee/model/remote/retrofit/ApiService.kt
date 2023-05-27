package com.example.promosee.model.remote.retrofit

import com.example.promosee.model.local.preference.CompanyModel
import com.example.promosee.model.local.preference.InfluencerModel
import com.example.promosee.model.remote.reponse.GetInfluencersResponse
import com.example.promosee.model.remote.reponse.LoginResponse
import com.example.promosee.model.remote.reponse.RegisterResponse
import com.example.promosee.model.remote.request.User
import com.google.gson.annotations.SerializedName
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query



interface ApiService {

//    @POST("login")
//    suspend fun userLogin(
//        @Field("username") username: String,
//        @Field("password") password: String
//    ) : LoginResponse

    @POST("login")
    suspend fun userLogin(@Body user: User): LoginResponse

//    @POST("login")
//    suspend fun userLogin(
//        @Query("username") username: String,
//        @Query("password") password: String
//    ) : LoginResponse

    @POST("register/businessowner")
    suspend fun companyRegister(
        @Body business_owner: CompanyModel,
    ) : RegisterResponse

    @POST("register/influencer")
    suspend fun influencerRegister(
        @Body influencer: InfluencerModel,
    ) : RegisterResponse


    @GET("getinfluencers")
    suspend fun getInfluencers(
        @Header("Authorization") token: String,
    ): GetInfluencersResponse


}