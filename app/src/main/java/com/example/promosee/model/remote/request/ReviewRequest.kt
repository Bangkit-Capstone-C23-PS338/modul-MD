package com.example.promosee.model.remote.request

import com.google.gson.annotations.SerializedName

data class ReviewRequest(
    @SerializedName("order_id")
    val order_id: String,
    @SerializedName("rating")
    val rating: Int,
    @SerializedName("comment")
    val comment: String
)