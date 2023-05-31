package com.example.promosee.view.company.mainCompany.ui.order

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.promosee.R
import com.example.promosee.databinding.ActivityOrderBinding

class OrderActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOrderBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}