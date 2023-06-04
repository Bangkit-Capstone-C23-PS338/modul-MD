package com.example.promosee.view.company.mainCompany.ui.detailInfluencer

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.promosee.R
import com.example.promosee.adapter.ProductAdapter
import com.example.promosee.databinding.ActivityInfluencerDetailBinding
import com.example.promosee.databinding.ActivityLoginBinding
import com.example.promosee.model.Result
import com.example.promosee.model.remote.reponse.ProductsItemInfluencer
import com.example.promosee.model.setStarRating
import com.example.promosee.view.ViewModelFactory
import com.example.promosee.view.company.mainCompany.MainCom
import com.example.promosee.view.company.mainCompany.ui.order.OrderActivity
import com.example.promosee.view.company.mainCompany.ui.reviews.ReviewsActivity
import com.example.promosee.view.login.LoginViewModel

class InfluencerDetailActivity : AppCompatActivity() {


    private lateinit var binding: ActivityInfluencerDetailBinding
    private lateinit var influencerDetailViewModel: InfluencerDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInfluencerDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setViewModel()
        setUpAction()
        val rating = 4.8
        setStarRating(rating,this)
        binding.ratingNumber.text = rating.toString()
        binding.backButton.setOnClickListener{ finish() }
        binding.btnReview.setOnClickListener{
            val moveIntent = Intent(this@InfluencerDetailActivity,ReviewsActivity::class.java)
            val getIntent: Intent = intent
            val username = getIntent.getStringExtra("username") as String
            moveIntent.putExtra("username",username)
            startActivity(moveIntent)
        }
    }

    private fun setViewModel() {
        influencerDetailViewModel = ViewModelProvider(
            this,
            ViewModelFactory.getInstance(application)
        )[InfluencerDetailViewModel::class.java]
    }

    private fun setUpAction() {
        val intent: Intent = intent
        val username: String = intent.getStringExtra("username") as String
        if(username != null){
            binding.usernameHead.text = username
            binding.usernameTitle.text = username

            binding.progressBar.visibility = View.VISIBLE
            binding.noProd.visibility = View.GONE
            influencerDetailViewModel.setUsername(username)
            influencerDetailViewModel.getInfluencrProducts().observe(this){result ->
                when(result){
                    is Result.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                        binding.noProd.visibility = View.GONE
                    }
                    is Result.Success -> {
                        binding.progressBar.visibility = View.GONE
                        binding.noProd.visibility = View.GONE
                        val allInfluencerProduct: List<ProductsItemInfluencer> = result.data.products as List<ProductsItemInfluencer>
                        Log.e("products view", allInfluencerProduct.toString())
                        if(allInfluencerProduct.isNotEmpty()){
                            addInfluencerProduct(allInfluencerProduct)
                        }else{
                            binding.noProd.visibility = View.VISIBLE
                        }
                    }
                    is Result.Error -> {
                        binding.progressBar.visibility = View.GONE
                        binding.noProd.visibility = View.GONE
                        val msg: String = getString(R.string.productFail)
                        Toast.makeText(
                            this@InfluencerDetailActivity,
                            msg,
                            Toast.LENGTH_SHORT
                        ).show()

                    }
                }

            }
        }

    }


    private fun addInfluencerProduct(products: List<ProductsItemInfluencer>) {
        val username: String = intent.getStringExtra("username") as String
        // sambungan ke adapter
        binding.influProd.layoutManager = LinearLayoutManager(this)
        val adapter = ProductAdapter(products, "bussiness")
        adapter.setUsername(username)
        binding.influProd.adapter = adapter

    }
}