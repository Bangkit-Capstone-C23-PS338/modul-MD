package com.example.promosee.model.remote.request

import com.google.gson.annotations.SerializedName

data class PostProductRequest(

    @field:SerializedName("social_media_type")
    val socialMediaType: String? = null,

    @field:SerializedName("price")
    val price: Int? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("description")
    val description: String? = null,

    @field:SerializedName("to_do")
    val toDo: List<String?>? = null,

    @field:SerializedName("posting_date")
    val postingDate: String? = null


)