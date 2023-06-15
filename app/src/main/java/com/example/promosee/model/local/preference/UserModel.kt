package com.example.promosee.model.local.preference

data class UserModel(
    val username: String,
    val userid: String,
    var access_token: String,
    val user_access: String,
    val password: String
)