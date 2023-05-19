package com.example.promosee.model.remote.reponse

import com.google.gson.annotations.SerializedName

data class LoginResponse(

	@field:SerializedName("access_token")
	val accessToken: String? = null,

	@field:SerializedName("token_type")
	val tokenType: String? = null,

	@field:SerializedName("userid")
	val userid: String? = null,

	@field:SerializedName("username")
	val username: String? = null
)

data class RegisterResponse(

	@field:SerializedName("message")
	val message: String? = null,

)
