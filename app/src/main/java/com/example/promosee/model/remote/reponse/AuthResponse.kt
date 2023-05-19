package com.example.promosee.model.remote.reponse

import com.google.gson.annotations.SerializedName

data class LoginResponse(

	@field:SerializedName("company_name")
	val companyName: String? = null,

	@field:SerializedName("categories")
	val categories: List<String?>? = null,

	@field:SerializedName("userid")
	val userid: String? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("username")
	val username: String? = null
)
