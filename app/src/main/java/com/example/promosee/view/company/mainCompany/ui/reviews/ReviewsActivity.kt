package com.example.promosee.view.company.mainCompany.ui.reviews

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.promosee.adapter.ReviewsAdapter
import com.example.promosee.databinding.ActivityReviewsBinding
import com.example.promosee.model.Result
import com.example.promosee.model.remote.reponse.ReviewsItem
import com.example.promosee.view.ViewModelFactory

class ReviewsActivity : AppCompatActivity() {


    private lateinit var binding: ActivityReviewsBinding
    private lateinit var reviewsViewModel: ReviewsViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReviewsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // back button
        setupViewmode()
        binding.backButton.setOnClickListener{ finish() }
        setupAction()

    }

    private fun setupViewmode() {
        reviewsViewModel = ViewModelProvider(
            this,
            ViewModelFactory.getInstance(this)
        )[ReviewsViewModel::class.java]
    }

    private fun setupAction() {
        val intent: Intent = intent
        val username = intent.getStringExtra("username") as String

        // get data from view Model -- API still not finish --
        // connect to adapter
        reviewsViewModel.setUsername(username)
        reviewsViewModel.getReviews().observe(this){result ->
            when(result) {
                is Result.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.noReview.visibility = View.GONE
                }

                is Result.Success -> {
                    binding.progressBar.visibility = View.GONE
                    binding.noReview.visibility = View.GONE
                    val reviews = result.data.reviews as List<ReviewsItem>
                    if(reviews.isEmpty()){
                        binding.noReview.visibility = View.VISIBLE
                    }
                    addInfluencerReviews(reviews)
                }

                is Result.Error -> {
                    Log.e("error msg", result.error)
                    if (result.error.trim() == "HTTP 401") {
                        binding.progressBar.visibility = View.GONE
                    }
                }
            }
        }



    }

    private fun addInfluencerReviews(reviews: List<ReviewsItem>) {
        // sambungan ke adapter
        binding.rvReviews.layoutManager = LinearLayoutManager(this)
        val adapter = ReviewsAdapter(reviews)
        binding.rvReviews.adapter = adapter
    }
}