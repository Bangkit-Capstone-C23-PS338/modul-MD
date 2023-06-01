package com.example.promosee.view.company.mainCompany.ui.reviews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.promosee.R
import com.example.promosee.adapter.ProductAdapter
import com.example.promosee.adapter.ReviewsAdapter
import com.example.promosee.databinding.ActivityInfluencerDetailBinding
import com.example.promosee.databinding.ActivityReviewsBinding

class ReviewsActivity : AppCompatActivity() {


    private lateinit var binding: ActivityReviewsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReviewsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // back button
        binding.backButton.setOnClickListener{ finish() }
        setupAction()

    }

    private fun setupAction() {

        // get data from view Model -- API still not finish --


        // connect to adapter
        addInfluencerReviews()

    }

    private fun addInfluencerReviews() {
        // sambungan ke adapter
        binding.rvReviews.layoutManager = LinearLayoutManager(this)
        val adapter = ReviewsAdapter()
        binding.rvReviews.adapter = adapter
    }
}