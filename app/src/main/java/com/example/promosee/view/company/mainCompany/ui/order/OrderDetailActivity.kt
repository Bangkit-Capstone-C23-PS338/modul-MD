package com.example.promosee.view.company.mainCompany.ui.order

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.promosee.R
import com.example.promosee.databinding.ActivityOrderDetailBinding

class OrderDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOrderDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViewModel()
        setupView()
    }

    private fun setupViewModel() {

    }
    private fun setupView() {

    }

}