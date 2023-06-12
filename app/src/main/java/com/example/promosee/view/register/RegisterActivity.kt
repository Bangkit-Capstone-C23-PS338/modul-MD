package com.example.promosee.view.register

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.promosee.R
import com.example.promosee.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragmentManager = supportFragmentManager
        val chooseFragment = RegisterChooseFragment()
        val fragment = fragmentManager.findFragmentByTag(RegisterChooseFragment::class.java.simpleName)

        if (fragment !is RegisterChooseFragment) {
            fragmentManager
                .beginTransaction()
                .add(R.id.frame_container, chooseFragment, RegisterChooseFragment::class.java.simpleName)
                .commit()
        }
    }
}