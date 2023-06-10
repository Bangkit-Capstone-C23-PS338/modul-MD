package com.example.promosee.model.remote.reponse

import com.google.gson.annotations.SerializedName

data class AddReviewResponse(

	@field:SerializedName("order_courier")
	val orderCourier: String? = null,

	@field:SerializedName("brief")
	val brief: String? = null,

	@field:SerializedName("business_owner")
	val businessOwner: String? = null,

	@field:SerializedName("selected_product")
	val selectedProduct: SelectedProduct? = null,

	@field:SerializedName("product_name")
	val productName: String? = null,

	@field:SerializedName("content_link")
	val contentLink: String? = null,

	@field:SerializedName("order_date")
	val orderDate: String? = null,

	@field:SerializedName("sender_address")
	val senderAddress: String? = null,

	@field:SerializedName("product_type")
	val productType: String? = null,

	@field:SerializedName("influencer_username")
	val influencerUsername: String? = null,

	@field:SerializedName("receiver_address")
	val receiverAddress: String? = null,

	@field:SerializedName("posting_date")
	val postingDate: String? = null,

	@field:SerializedName("order_id")
	val orderId: String? = null,

	@field:SerializedName("payment_date")
	val paymentDate: String? = null,

	@field:SerializedName("payment_method")
	val paymentMethod: String? = null,

	@field:SerializedName("status")
	val status: String? = null,

	@field:SerializedName("product_link")
	val productLink: String? = null
)

data class SelectedProduct(

	@field:SerializedName("social_media_type")
	val socialMediaType: String? = null,

	@field:SerializedName("price")
	val price: Any? = null,

	@field:SerializedName("product_id")
	val productId: Int? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("to_do")
	val toDo: List<String?>? = null
)
