package com.example.promosee.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.promosee.databinding.ActivityBoardingBinding
import com.example.promosee.view.login.LoginActivity

class BoardingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBoardingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBoardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnContinue.setOnClickListener{
            val intent = Intent(this@BoardingActivity, LoginActivity::class.java)
            startActivity(intent)
        }

    }
}