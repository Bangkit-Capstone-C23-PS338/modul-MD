package com.example.promosee.model.local.preference

import java.util.Date

data class OrderModel (
    val id: Int,
    val companyUsername: String,
    val influencerUsername: String,
    val status: String,
    val orderDate: Date,
    val postingDate: Date,
    val productName: String,
    val productUrl: String,
    val productType: String,
    val brief: String,
    val promotionPackage: String,
    val promotionMedia: String
)