package com.example.promosee.model.local.preference

import com.example.promosee.model.remote.reponse.ProductsItemInfluencer

data class OrderModel (
    val id: String? = "",
    val companyUsername: String? = "",
    val influencerUsername: String,
    val status: String? = "",
    val orderDate: String? = "",
    val postingDate: String,
    val productName: String,
    val productUrl: String,
    val productType: String,
    val brief: String,
    val orderCourier: String,
    val senderAddress: String,
    val orderAddress: String,
    val orderPaymentDate: String,
    val paymentMethod: String,
    val selectedPackage: ProductsItemInfluencer,
    val contentLink: String? = ""
)

object DummyOrders{
    private val dummyProduct = ProductsItemInfluencer(
        socialMediaType = "Instagram",
        price = 100000,
        name = "Paket A",
        toDo = listOf("duduk disana", "duduk disitu")
    )
    private val dummyOrder1 = OrderModel(
        "1",
        "burjoni_s",
        "iu",
        "waiting",
        "01-01-2023",
        "01-01-2023",
        "tupperware",
        "https://th.bing.com/th/id/OIP.UBoeOHjxhhV2RlLnYd13lwHaHj?pid=ImgDet&rs=1",
        "Water Bottle",
        "Pencahayaannya yang bagus ya",
        "SiLambat",
        "Jl. Prof Soedarto No.4",
        "Jl. Prof Soedarto No.5",
        "01-01-2023",
        "BCA",
        dummyProduct
    )
    private val dummyOrder2 = OrderModel(
        "2",
        "burjoni_s",
        "iu",
        "processing",
        "01-01-2023",
        "01-01-2023",
        "tupperware",
        "https://th.bing.com/th/id/OIP.UBoeOHjxhhV2RlLnYd13lwHaHj?pid=ImgDet&rs=1",
        "Water Bottle",
        "Pencahayaannya yang bagus ya",
        "SiLambat",
        "Jl. Prof Soedarto No.4",
        "Jl. Prof Soedarto No.5",
        "01-01-2023",
        "BCA",
        dummyProduct
    )
    private val dummyOrder3 = OrderModel(
        "3",
        "burjoni_s",
        "iu",
        "failed",
        "01-01-2023",
        "01-01-2023",
        "tupperware",
        "https://th.bing.com/th/id/OIP.UBoeOHjxhhV2RlLnYd13lwHaHj?pid=ImgDet&rs=1",
        "Water Bottle",
        "Pencahayaannya yang bagus ya",
        "SiLambat",
        "Jl. Prof Soedarto No.4",
        "Jl. Prof Soedarto No.5",
        "01-01-2023",
        "BCA",
        dummyProduct
    )
    private val dummyOrder4 = OrderModel(
        "4",
        "burjoni_s",
        "iu",
        "done",
        "01-01-2023",
        "01-01-2023",
        "tupperware",
        "https://th.bing.com/th/id/OIP.UBoeOHjxhhV2RlLnYd13lwHaHj?pid=ImgDet&rs=1",
        "Water Bottle",
        "Pencahayaannya yang bagus ya",
        "SiLambat",
        "Jl. Prof Soedarto No.4",
        "Jl. Prof Soedarto No.5",
        "01-01-2023",
        "BCA",
        dummyProduct
    )
    val listOrders: List<OrderModel> = listOf(dummyOrder1, dummyOrder2, dummyOrder3, dummyOrder4)
}