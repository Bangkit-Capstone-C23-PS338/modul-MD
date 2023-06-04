package com.example.promosee.model.remote.reponse

import com.example.promosee.model.local.preference.CompanyModel
import com.example.promosee.model.local.preference.OrderModel
import com.google.gson.annotations.SerializedName

data class OrderResponse(
    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("order")
    val order: OrderItem,
)

data class OrderItem(
    @field:SerializedName("order_id")
    val order_id: String? = "",

    @field:SerializedName("order_date")
    val order_date: Any? = "",

    @field:SerializedName("influencer_username")
    val influencer_username: String,

    @field:SerializedName("business_owner")
    val business_owner: String? = "",

    @field:SerializedName("product_name")
    val product_name: String,

    @field:SerializedName("product_type")
    val product_type: String,

    @field:SerializedName("product_link")
    val product_link: String,

    @field:SerializedName("sender_address")
    val sender_address: String,

    @field:SerializedName("receiver_address")
    val receiver_address: String,

    @field:SerializedName("order_courier")
    val order_courier: String,

    @field:SerializedName("payment_method")
    val payment_method: String,

    @field:SerializedName("payment_status")
    val payment_status: String? = " ",

    @field:SerializedName("brief")
    val brief: String,

    @field:SerializedName("payment_date")
    val payment_date: Any? = "",

    @field:SerializedName("selected_package")
    val selected_package: ProductsItemInfluencer,
)

