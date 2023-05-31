package com.example.promosee.model.local.preference

import java.text.SimpleDateFormat
import java.util.Date

data class OrderModel (
    val id: Int, // incremental
    val companyUsername: String,
    val influencerUsername: String,
    val status: String,
    val orderDate: String,
    val postingDate: String,
    val productName: String,
    val productUrl: String,
    val productType: String,
    val brief: String,
    val promotionPackage: String,// dari model product
    val promotionMedia: String, // dari model product
    val orderCourier: String,
    val senderAddress: String,
    val orderAddress: String,
    val orderPaymentDate: String,
    val paymentMethod: String,
    val totalPayment: Int // dari model product
    // val package: Product
)

object DummyOrders{
    val dummyOrder1 = OrderModel(
        1,
        "burjoni_s",
        "iu",
        "waiting",
        "01-01-2023",
        "01-01-2023",
        "tupperware",
        "https://th.bing.com/th/id/OIP.UBoeOHjxhhV2RlLnYd13lwHaHj?pid=ImgDet&rs=1",
        "Water Bottle",
        "Pencahayaannya yang bagus ya",
        "Paket A",
        "Instagram",
        "SiLambat",
        "Jl. Prof Soedarto No.4",
        "Jl. Prof Soedarto No.5",
        "01-01-2023",
        "BCA",
        1000000
    )
    val dummyOrder2 = OrderModel(
        2,
        "burjoni_s",
        "iu",
        "processing",
        "01-01-2023",
        "01-01-2023",
        "tupperware",
        "https://th.bing.com/th/id/OIP.UBoeOHjxhhV2RlLnYd13lwHaHj?pid=ImgDet&rs=1",
        "Water Bottle",
        "Pencahayaannya yang bagus ya",
        "Paket A",
        "Instagram",
        "SiLambat",
        "Jl. Prof Soedarto No.4",
        "Jl. Prof Soedarto No.5",
        "01-01-2023",
        "BCA",
        1000000
    )
    val dummyOrder3 = OrderModel(
        3,
        "burjoni_s",
        "iu",
        "failed",
        "01-01-2023",
        "01-01-2023",
        "tupperware",
        "https://th.bing.com/th/id/OIP.UBoeOHjxhhV2RlLnYd13lwHaHj?pid=ImgDet&rs=1",
        "Water Bottle",
        "Pencahayaannya yang bagus ya",
        "Paket A",
        "Instagram",
        "SiLambat",
        "Jl. Prof Soedarto No.4",
        "Jl. Prof Soedarto No.5",
        "01-01-2023",
        "BCA",
        1000000
    )
    val dummyOrder4 = OrderModel(
        4,
        "burjoni_s",
        "iu",
        "done",
        "01-01-2023",
        "01-01-2023",
        "tupperware",
        "https://th.bing.com/th/id/OIP.UBoeOHjxhhV2RlLnYd13lwHaHj?pid=ImgDet&rs=1",
        "Water Bottle",
        "Pencahayaannya yang bagus ya",
        "Paket A",
        "Instagram",
        "SiLambat",
        "Jl. Prof Soedarto No.4",
        "Jl. Prof Soedarto No.5",
        "01-01-2023",
        "BCA",
        1000000
    )
    val listOrders: List<OrderModel> = listOf(dummyOrder1, dummyOrder2, dummyOrder3, dummyOrder4)
}