package com.example.promosee.model.remote.reponse

import com.example.promosee.model.local.preference.CompanyModel
import com.example.promosee.model.local.preference.OrderModel
import com.google.gson.annotations.SerializedName

data class OrderResponse(
    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("order")
    val order: OrderModel,

    @field:SerializedName("business_owner")
    val company: CompanyModel,
)