package com.example.promosee.view.influencer.mainInflu.ui.review

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.promosee.R
import com.example.promosee.databinding.ActivityProductBinding
import com.example.promosee.databinding.ActivityReviewFormBinding
import com.example.promosee.databinding.ActivityReviewsBinding
import com.example.promosee.model.setStarRating

class ReviewFormActivity : AppCompatActivity() {


    private lateinit var binding: ActivityReviewFormBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReviewFormBinding.inflate(layoutInflater)

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
    }

}