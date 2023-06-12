package com.example.promosee.view.influencer.mainInflu.ui.product

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.promosee.adapter.ProductAdapter
import com.example.promosee.databinding.ActivityProductBinding
import com.example.promosee.model.Result
import com.example.promosee.model.remote.reponse.ProductsItemInfluencer
import com.example.promosee.view.ViewModelFactory
import com.example.promosee.view.influencer.mainInflu.ui.home.HomeFragmentInfluencer

class ProductActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductBinding
    private lateinit var productViewModel: ProductViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductBinding.inflate(layoutInflater)

        setupViewmodel()
        setupAction()

        setContentView(binding.root)
    }

    override fun onRestart() {
        super.onRestart()
        finish()
        overridePendingTransition(0, 0)
        startActivity(intent)
        overridePendingTransition(0, 0)
    }

    override fun onBackPressed() {
        val fragment = supportFragmentManager.findFragmentByTag("HomeFragmentInfluencer")
        if (fragment != null && fragment is HomeFragmentInfluencer) {
            fragment.setupAction()
        }
        super.onBackPressed()
    }

    private fun setupAction() {

        binding.backButton.setOnClickListener{finish()}
        binding.addProduct.setOnClickListener{
            val moveIntent = Intent(this,ProductFormActivity::class.java)
            moveIntent.putExtra("form_type", "create")
            startActivity(moveIntent)
        }

        productViewModel.getProduct().observe(this){result ->
            when(result){
                is Result.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Result.Success -> {
                    binding.progressBar.visibility = View.GONE
                    val allInfluencerProduct: List<ProductsItemInfluencer> = result.data.products as List<ProductsItemInfluencer>
                    if(allInfluencerProduct.isNotEmpty()){
                        addInfluencerProduct(allInfluencerProduct)
                    }
                }
                is Result.Error -> {
                    Log.e("error msg", result.error)
                    if(result.error.trim() == "HTTP 401"){
                        binding.progressBar.visibility = View.GONE
                    }
                }
            }
        }
    }

    private fun addInfluencerProduct(products: List<ProductsItemInfluencer>) {
        // sambungan ke adapter
        binding.rvProduct.layoutManager = LinearLayoutManager(this)
        val adapter = ProductAdapter(products,"influencer")
        binding.rvProduct.adapter = adapter

    }

    private fun setupViewmodel() {
        productViewModel = ViewModelProvider(
            this,
            ViewModelFactory.getInstance(application)
        )[ProductViewModel::class.java]
    }
}