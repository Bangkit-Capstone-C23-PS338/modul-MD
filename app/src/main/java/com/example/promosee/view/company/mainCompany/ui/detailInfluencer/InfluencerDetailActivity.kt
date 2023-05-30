package com.example.promosee.view.company.mainCompany.ui.detailInfluencer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.promosee.R
import com.example.promosee.adapter.ProductAdapter
import com.example.promosee.databinding.ActivityInfluencerDetailBinding
import com.example.promosee.databinding.ActivityLoginBinding

class InfluencerDetailActivity : AppCompatActivity() {


    private lateinit var binding: ActivityInfluencerDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInfluencerDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setViewModel()
        setUpAction()
    }

    private fun setViewModel() {

    }

    private fun setUpAction() {
        val intent: Intent = intent
        val username: String = intent.getStringExtra("username") as String

        binding.influProd.layoutManager = LinearLayoutManager(this)
        val adapter = ProductAdapter()
        binding.influProd.adapter = adapter


    }
}