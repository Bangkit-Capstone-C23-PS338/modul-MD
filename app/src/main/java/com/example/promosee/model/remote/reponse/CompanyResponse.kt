package com.example.promosee.model.remote.reponse

import com.google.gson.annotations.SerializedName

data class GetInfluencersResponse(

	@field:SerializedName("influencers")
	val influencers: List<InfluencersItem?>? = null
)

data class ProductsItem(

	@field:SerializedName("social_media_type")
	val socialMediaType: String? = null,

	@field:SerializedName("price")
	val price: Any? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("to_do")
	val toDo: List<String?>? = null
)

data class InfluencersItem(

	@field:SerializedName("tt_followers")
	val ttFollowers: Int? = null,

	@field:SerializedName("yt_followers")
	val ytFollowers: Int? = null,

	@field:SerializedName("password")
	val password: String? = null,

	@field:SerializedName("tt_username")
	val ttUsername: String? = null,

	@field:SerializedName("yt_username")
	val ytUsername: String? = null,

	@field:SerializedName("categories")
	val categories: List<String?>? = null,

	@field:SerializedName("ig_followers")
	val igFollowers: Int? = null,

	@field:SerializedName("userid")
	val userid: String? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("username")
	val username: String? = null,

	@field:SerializedName("ig_username")
	val igUsername: String? = null,

	@field:SerializedName("products")
	val products: List<ProductsItem?>? = null
)
