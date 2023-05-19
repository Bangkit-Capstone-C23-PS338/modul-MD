package com.example.promosee.ui.boarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.promosee.R
import com.example.promosee.databinding.ActivityBoardingBinding
import com.example.promosee.ui.register.RegisterActivity

class BoardingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBoardingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBoardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnContinue.setOnClickListener {
            val intentToRegister = Intent(this@BoardingActivity, RegisterActivity::class.java)
            startActivity(intentToRegister)
        }
    }
}