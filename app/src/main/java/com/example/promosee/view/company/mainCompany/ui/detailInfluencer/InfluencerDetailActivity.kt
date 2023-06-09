package com.example.promosee.view.company.mainCompany.ui.detailInfluencer

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.promosee.R
import com.example.promosee.adapter.ProductAdapter
import com.example.promosee.databinding.ActivityInfluencerDetailBinding
import com.example.promosee.model.Result
import com.example.promosee.model.remote.reponse.ProductsItemInfluencer
import com.example.promosee.model.setStarRating
import com.example.promosee.view.ViewModelFactory
import com.example.promosee.view.company.mainCompany.ui.reviews.ReviewsActivity
import java.text.DecimalFormat

class InfluencerDetailActivity : AppCompatActivity() {


    private lateinit var binding: ActivityInfluencerDetailBinding
    private lateinit var influencerDetailViewModel: InfluencerDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInfluencerDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setViewModel()
        setUpAction()



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

    private fun formatNumber(number: Int): String {
        val suffixes = listOf("", "k", "m", "b") // Suffixes for thousands, millions, and billions

        val absNumber = Math.abs(number.toLong()) // Get the absolute value of the number
        if (absNumber < 1000) {
            return number.toString() // Return the number as it is if it's less than 1000
        }

        val exp = (Math.log10(absNumber.toDouble()) / 3).toInt() // Determine the exponent for the suffix
        val formattedValue = String.format("%.1f%s", absNumber / Math.pow(1000.0, exp.toDouble()), suffixes[exp])
        return if (number < 0) "-$formattedValue" else formattedValue // Add a negative sign if the number was negative
    }

    private fun setUpAction() {
        val intent: Intent = intent
        val username: String = intent.getStringExtra("username") as String
        Log.e("foll", "sblm username")
        if(username != null){
            Log.e("foll", "setelah username" + username)
            binding.usernameHead.text = username
            binding.usernameTitle.text = username

            binding.progressBar.visibility = View.VISIBLE
            binding.noProd.visibility = View.GONE

            influencerDetailViewModel.setUsername(username)

            influencerDetailViewModel.getInfluencerProfile().observe(this){result ->
                when(result){
                    is Result.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                        binding.noProd.visibility = View.GONE
                    }
                    is Result.Success -> {

                        Log.e("foll", "setelah success" + result.data.toString())
                        binding.progressBar.visibility = View.GONE
                        binding.noProd.visibility = View.GONE
                        // set rating

                        if(result.data.influencers?.igFollowers != null){
                            binding.followInstas.text = getString(R.string.Follower_insta,
                                result.data.influencers?.igFollowers?.let { formatNumber(it.toInt()) })
                        }else{
                            binding.followInstas.text = getString(R.string.Follower_insta,
                                "0")
                        }
                        if(result.data.influencers?.ttFollowers != null){
                            binding.followTiktoks.text = getString(R.string.Follower_insta,
                                result.data.influencers?.ttFollowers?.let { formatNumber(it.toInt()) })
                        }else{
                            binding.followTiktoks.text = "0"
                        }
                        if(result.data.influencers?.ytFollowers != null){
                            binding.followYoutubes.text = getString(R.string.Follower_insta,
                                result.data.influencers?.ytFollowers?.let { formatNumber(it.toInt()) })
                        }else{
                            binding.followYoutubes.text = "0"
                        }


                        val decimalFormat = DecimalFormat("#.#")
                        val formattedNumber = decimalFormat.format(result.data.influencers?.rating?.toDouble()).toString()
                        val rating: String = formattedNumber
                        setStarRating(rating.toDouble(),this)
                        binding.ratingNumber.text = rating.toString()



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