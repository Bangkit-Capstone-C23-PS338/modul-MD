package com.example.promosee.model.remote.reponse

import com.google.gson.annotations.SerializedName


data class getInfleuncerProfileResponse(

	@field:SerializedName("influencers")
	val influencers: getInfluencerItem? = null
)

data class getInfluencerItem(

	@field:SerializedName("photo_profile_url")
	val photoProfileUrl: String? = null,

	@field:SerializedName("tt_username")
	val ttUsername: String? = null,

	@field:SerializedName("address")
	val address: String? = null,

	@field:SerializedName("userid")
	val userid: String? = null,

	@field:SerializedName("products")
	val products: List<ProductsItemInfluencer?>? = null,

	@field:SerializedName("tt_followers")
	val ttFollowers: Int? = null,

	@field:SerializedName("yt_followers")
	val ytFollowers: Int? = null,

	@field:SerializedName("password")
	val password: String? = null,

	@field:SerializedName("yt_username")
	val ytUsername: String? = null,

	@field:SerializedName("categories")
	val categories: List<String?>? = null,

	@field:SerializedName("ig_followers")
	val igFollowers: Int? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("ig_username")
	val igUsername: String? = null,

	@field:SerializedName("username")
	val username: String? = null,

	@field:SerializedName("rating")
	val rating: String? = null
)

data class PostProductResponse(

	@field:SerializedName("detail")
	val detail: String? = null

)

data class UpdateProductResponse(

	@field:SerializedName("message")
	val message: String? = null

)

data class deleteProductResponse(

	@field:SerializedName("message")
	val message: String? = null

)

data class getProductItemResponse(

	@field:SerializedName("social_media_type")
	val socialMediaType: String? = null,

	@field:SerializedName("ig_uname")
	val igUname: String? = null,

	@field:SerializedName("price")
	val price: Int? = null,

	@field:SerializedName("ig_foll")
	val igFoll: Int? = null,

	@field:SerializedName("product_id")
	val productId: Int? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("to_do")
	val toDo: List<String?>? = null
)
