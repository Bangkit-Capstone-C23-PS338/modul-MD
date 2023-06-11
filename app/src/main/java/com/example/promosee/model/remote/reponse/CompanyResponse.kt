package com.example.promosee.model.remote.reponse

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class GetInfluencersResponse(

	@field:SerializedName("influencers")
	val influencers: List<InfluencersItem?>? = null
)

data class PostReviewResponse(

	@field:SerializedName("message")
	val message: String? = null
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

	@field:SerializedName("photo_profile_url")
	val photoProfileUrl: String? = null,

	@field:SerializedName("tt_username")
	val ttUsername: String? = null,

	@field:SerializedName("address")
	val address: String? = null,

	@field:SerializedName("rating")
	val rating: Any? = null,

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

	@field:SerializedName("reviews")
	val reviews: List<ReviewsItemResponse?>? = null,

	@field:SerializedName("categories")
	val categories: List<String?>? = null,

	@field:SerializedName("ig_followers")
	val igFollowers: Int? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("username")
	val username: String? = null,

	@field:SerializedName("ig_username")
	val igUsername: String? = null
)

data class GetInfluencerProductReponse(

	@field:SerializedName("products")
	val products: List<ProductsItemInfluencer?>? = null
)

@Parcelize
data class ProductsItemInfluencer(

	@field:SerializedName("social_media_type")
	val socialMediaType: String? = null,

	@field:SerializedName("price")
	val price: Int? = null,

	@field:SerializedName("product_id")
	val productId: Int? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("to_do")
	val toDo: List<String?>? = null,

	@field:SerializedName("id")
	val id: Int? = null
): Parcelable

data class ReviewsResponse(
	@field:SerializedName("reviews")
	val reviews: List<ReviewsItem?>? = null
)

data class ReviewsItem(

	@field:SerializedName("order_date")
	val orderDate: String? = null,

	@field:SerializedName("rating")
	val rating: Int? = null,

	@field:SerializedName("comment")
	val comment: String? = null,

	@field:SerializedName("company_name")
	val companyName: String? = null,

	@field:SerializedName("order_id")
	val orderId: String? = null,

	@field:SerializedName("time_reviewed")
	val timeReviewed: String? = null,
)
