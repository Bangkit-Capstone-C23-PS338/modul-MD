package com.example.promosee.model.remote.retrofit

import com.example.promosee.model.local.preference.CompanyModel
import com.example.promosee.model.local.preference.InfluencerModel
import com.example.promosee.model.local.preference.OrderModel
import com.example.promosee.model.remote.reponse.GetInfluencerProductReponse
import com.example.promosee.model.remote.reponse.GetInfluencersResponse
import com.example.promosee.model.remote.reponse.LoginResponse
import com.example.promosee.model.remote.reponse.LogoutResponse
import com.example.promosee.model.remote.reponse.OrderResponse
import com.example.promosee.model.remote.reponse.RegisterResponse
import com.example.promosee.model.remote.reponse.ReviewsResponse
import com.example.promosee.model.remote.reponse.getInfleuncerProfileResponse
import com.example.promosee.model.remote.request.User
import com.google.gson.annotations.SerializedName
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query



interface ApiService {

    @POST("login")
    suspend fun userLogin(@Body user: User): LoginResponse

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

    @GET("influencers/{username}/products")
    suspend fun getInfluencerProducts(
        @Header("Authorization") token: String,
        @Path("username") username: String
    ): GetInfluencerProductReponse

    @POST("logout")
    suspend fun userLogout(
        @Header("Authorization") token: String,
    ): LogoutResponse

    @GET("review_influencer/{username}")
    suspend fun getReviews(
        @Header("Authorization") token: String,
        @Path("username") username: String
    ): ReviewsResponse

    @GET("getinfluencer/{username}")
    suspend fun getInfluencerProfile(
        @Header("Authorization") token: String,
        @Path("username") username: String
    ): getInfleuncerProfileResponse
    @POST("add_influencer_order/{influencer_username}")
    suspend fun createOrder(
        @Header("Authorization") token: String,
        @Body order: OrderModel,
        @Path("influencer_username") influencerUsername: String
    ): OrderResponse

}