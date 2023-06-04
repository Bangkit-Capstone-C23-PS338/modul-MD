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

    fun setProduct(Name : String, Descriton : String, Todo : String, Sosmed : String){
        name = Name
        descriton = Descriton
        todo = Todo
        sosmed = Sosmed
    }

    fun getProduct() = influencerRepository.getInfluencerProduct()

    fun createProduct() = influencerRepository.postInfluencerProduct(name = name, description = descriton, todo = todo, socialMediaType = sosmed, price = "50000")


}