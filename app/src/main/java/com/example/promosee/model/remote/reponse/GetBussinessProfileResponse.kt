package com.example.promosee.model.remote.reponse

import com.google.gson.annotations.SerializedName

data class GetBussinessProfileResponse(

	@field:SerializedName("password")
	val password: String? = null,

	@field:SerializedName("influencer_rank")
	val influencerRank: List<String?>? = null,

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
