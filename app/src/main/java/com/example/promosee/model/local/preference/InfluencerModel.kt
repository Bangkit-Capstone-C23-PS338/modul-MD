package com.example.promosee.model.local.preference

data class InfluencerModel (
    val username: String,
    val email: String,
    val password: String,
    val categories: List<String>,
    val ig_username: String = "",
    val ig_followers: Int = 0,
    val tt_username: String = "",
    val tt_followers: Int = 0,
    val yt_username: String = "",
    val yt_followers: Int = 0,
)

