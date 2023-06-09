package com.example.promosee.model.remote.request

import com.google.gson.annotations.SerializedName

data class UpdateOrderRequest(

    @field:SerializedName("content_link")
    val content_link: String? = "",

    @field:SerializedName("status")
    val status: String,
)