package com.example.promosee.view.company.mainCompany.ui.reviews

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.promosee.R
import com.example.promosee.databinding.ActivityReviewFormBinding
import com.example.promosee.model.Result
import com.example.promosee.model.remote.request.UpdateOrderRequest
import com.example.promosee.model.setStarRating
import com.example.promosee.view.ViewModelFactory
import com.example.promosee.view.company.mainCompany.MainCom

class ReviewFormActivity : AppCompatActivity() {


    private lateinit var binding: ActivityReviewFormBinding
    private lateinit var reviewsViewModel: ReviewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReviewFormBinding.inflate(layoutInflater)

        setupViewmode()
        setupAction()

        setContentView(binding.root)
    }

    private fun setupAction() {

        var rate = 0.0
        binding.star1.setOnClickListener{
            rate = 5.0
            setStarRating(rate,this)
        }
        binding.star2.setOnClickListener{
            rate = 4.0
            setStarRating(rate,this)
        }
        binding.star3.setOnClickListener{
            rate = 3.0
            setStarRating(rate,this)
        }
        binding.star4.setOnClickListener{
            rate = 2.0
            setStarRating(rate,this)
        }
        binding.star5.setOnClickListener{
            rate = 1.0
            setStarRating(rate,this)
        }



        binding.addBtn.setOnClickListener{
            val order_id = intent.getStringExtra("order_id")
            val comment = binding.edtDesc.text.toString()
            val link = intent.getStringExtra("link")

            if(comment != ""){
                if (order_id != null) {
                    reviewsViewModel.setReview(rate.toInt().toString(),comment,order_id)
                    reviewsViewModel.postReview().observe(this){result ->
                        when(result) {
                            is Result.Loading -> {
                                binding.progressBar.visibility = View.VISIBLE
                            }

                            is Result.Success -> {
                                binding.progressBar.visibility = View.GONE
                                val msg: String = getString(R.string.reviewAdded)
                                Toast.makeText(
                                    this,
                                    msg,
                                    Toast.LENGTH_SHORT
                                ).show()
                                if (link != null) {
                                    updateOrder(
                                        content_link = link,
                                        status = "done",
                                        order_id = order_id
                                    )
                                    val intentToMainCom =  Intent(this@ReviewFormActivity, MainCom::class.java)
                                    intentToMainCom.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                    startActivity(intentToMainCom)
                                    finish()
                                }
                            }
                            is Result.Error -> {
                                binding.progressBar.visibility = View.GONE
                                Log.e("error msg", result.error)
                                val msg: String = getString(R.string.reviewFail)
                                Toast.makeText(
                                    this,
                                    msg,
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }

                    }
                }
            }else{
                val msg: String = getString(R.string.fieldEmpty)
                Toast.makeText(
                    this,
                    msg,
                    Toast.LENGTH_SHORT
                ).show()
            }

        }

    }

    fun updateOrder(content_link:String = "", status: String, order_id: String){
        val update_data = UpdateOrderRequest(content_link, status)
        reviewsViewModel.updateOrder(update_data, order_id).observe(this){ result ->
            when(result){
                is Result.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Result.Success -> {
                    binding.progressBar.visibility = View.GONE
                    Log.d("Cek Update", result.data.message.toString())
                }
                is Result.Error -> {
                    binding.progressBar.visibility = View.GONE
                    val msg: String = getString(R.string.failed_to_update)
                    Toast.makeText(
                        applicationContext,
                        msg,
                        Toast.LENGTH_SHORT
                    ).show()
                    finish()
                }
            }
        }
    }

    private fun setupViewmode() {
        reviewsViewModel = ViewModelProvider(
            this,
            ViewModelFactory.getInstance(this)
        )[ReviewsViewModel::class.java]
    }





}