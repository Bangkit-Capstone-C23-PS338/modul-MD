package com.example.promosee.model.remote.retrofit

import com.example.promosee.model.local.preference.CompanyModel
import com.example.promosee.model.local.preference.InfluencerModel
import com.example.promosee.model.local.preference.OrderModel
import com.example.promosee.model.remote.reponse.GetInfluencerProductReponse
import com.example.promosee.model.remote.reponse.GetInfluencersResponse
import com.example.promosee.model.remote.reponse.GetOrderResponse
import com.example.promosee.model.remote.reponse.InfluencerItemResponse
import com.example.promosee.model.remote.reponse.LoginResponse
import com.example.promosee.model.remote.reponse.LogoutResponse
import com.example.promosee.model.remote.reponse.OrderItem
import com.example.promosee.model.remote.reponse.OrderResponse
import com.example.promosee.model.remote.reponse.PostProductResponse
import com.example.promosee.model.remote.reponse.PostRes
import com.example.promosee.model.remote.reponse.PostReviewResponse
import com.example.promosee.model.remote.reponse.RegisterResponse
import com.example.promosee.model.remote.reponse.ReviewsResponse
import com.example.promosee.model.remote.reponse.deleteProductResponse
import com.example.promosee.model.remote.reponse.getInfleuncerProfileResponse
import com.example.promosee.model.remote.reponse.getProductItemResponse
import com.example.promosee.model.remote.request.PostProductRequest
import com.example.promosee.model.remote.request.ReviewRequest
import com.example.promosee.model.remote.request.UpdateOrderRequest
import com.example.promosee.model.remote.request.UpdateProductRequest
import com.example.promosee.model.remote.request.User
import com.google.gson.annotations.SerializedName
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
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

    @GET("get_BusinessOwner_influencerrank_detail/{username}")
    suspend fun getInfluencersRank(
        @Header("Authorization") token: String,
        @Path("username") username: String
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

    @GET("influencer_reviews/{username}")
    suspend fun getReviews(
        @Header("Authorization") token: String,
        @Path("username") username: String
    ): ReviewsResponse

    @GET("getinfluencer/{username}")
    suspend fun getInfluencerProfile(
        @Header("Authorization") token: String,
        @Path("username") username: String
    ): getInfleuncerProfileResponse

    @GET("getinfluencer/{username}")
    suspend fun getInfluencerSearchResponse(
        @Header("Authorization") token: String,
        @Path("username") username: String
    ): getInfleuncerProfileResponse

    @POST("add_influencer_order/{influencer_username}")
    suspend fun createOrder(
        @Header("Authorization") token: String,
        @Body order_data: OrderItem,
        @Path("influencer_username") influencer_username: String
    ): OrderResponse

    @PUT("update_order/{order_id}")
    suspend fun updateOrder(
        @Header("Authorization") token: String,
        @Body update_data: UpdateOrderRequest,
        @Path("order_id") order_id: String
    ): PostRes

    @GET("orders_business_owner/{business_owner}")
    suspend fun getCompanyOrders(
        @Header("Authorization") token: String,
        @Path("business_owner") business_owner: String
    ): GetOrderResponse

    @GET("influencer_orders/{influencer}")
    suspend fun getInfluencerOrders(
        @Header("Authorization") token: String,
        @Path("influencer") influencer: String
    ): GetOrderResponse

    @GET("get_order_details/{order_id}")
    suspend fun getOrderDetail(
        @Header("Authorization") token: String,
        @Path("order_id") order_id: String
    ): OrderItem

    @POST("influencers/{username}/products")
    suspend fun createProduct(
        @Header("Authorization") token: String,
        @Body product: PostProductRequest,
        @Path("username") username: String
    ): PostRes

    @GET("influencers/{username}/products/{product_id}")
    suspend fun getProductItem(
        @Header("Authorization") token: String,
        @Path("username") username: String,
        @Path("product_id") product_id: String
    ): getProductItemResponse

    @PUT("influencers/{username}/products/{product_id}")
    suspend fun UpdateProductItem(
        @Header("Authorization") token: String,
        @Body product: UpdateProductRequest,
        @Path("username") username: String,
        @Path("product_id") product_id: String
    ): UpdateProductRequest

    @DELETE("influencers/{username}/products/{product_id}")
    suspend fun deleteProductItem(
        @Header("Authorization") token: String,
        @Path("username") username: String,
        @Path("product_id") product_id: String
    ): deleteProductResponse

    @POST("add_influencer_review/{product_id}")
    suspend fun PostReview(
        @Header("Authorization") token: String,
        @Path("product_id") product_id: String,
        @Body review: ReviewRequest,
    ): PostReviewResponse

}