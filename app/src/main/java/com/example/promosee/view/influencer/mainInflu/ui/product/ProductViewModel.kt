package com.example.promosee.view.influencer.mainInflu.ui.product

import androidx.lifecycle.ViewModel
import com.example.promosee.model.repository.InfluencerRepository

class ProductViewModel(
    private val influencerRepository: InfluencerRepository
) : ViewModel() {

    private lateinit var name : String
    private lateinit var descriton : String
    private lateinit var todo : String
    private lateinit var sosmed : String
    private lateinit var price : String

    private lateinit var productid : String

    fun setProduct(Name : String, Descriton : String, Todo : String, Sosmed : String, Price: String){
        name = Name
        descriton = Descriton
        todo = Todo
        sosmed = Sosmed
        price = Price
    }

    fun setProductId(productId: String){
        productid = productId
    }

    fun getProduct() = influencerRepository.getInfluencerProduct()
    fun createProduct() = influencerRepository.postInfluencerProduct(name = name, description = descriton, todo = todo, socialMediaType = sosmed, price = price)
    fun getProductItem() = influencerRepository.getProductItem(productid)
    fun updateProductItem() = influencerRepository.updateInfluencerProduct(name = name, description = descriton, todo = todo, socialMediaType = sosmed, price = price, id = productid)
    fun deleteProductItem() = influencerRepository.deleteProductitem(productid)
}