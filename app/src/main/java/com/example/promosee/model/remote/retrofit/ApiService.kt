package com.example.promosee.model.remote.retrofit

import com.example.promosee.model.remote.reponse.LoginResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

//    @FormUrlEncoded
//    @POST("login")
//    suspend fun userLogin(
//        @Field("username") username: String,
//        @Field("password") password: String
//    ) : LoginResponse

    @POST("login")
    suspend fun userLogin(
        @Query("username") username: String,
        @Query("password") password: String
    ) : LoginResponse

}