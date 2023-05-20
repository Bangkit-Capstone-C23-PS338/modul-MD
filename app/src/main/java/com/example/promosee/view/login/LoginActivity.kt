package com.example.promosee.view.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.promosee.R
import com.example.promosee.databinding.ActivityLoginBinding
import com.example.promosee.view.ViewModelFactory
import com.example.promosee.model.Result
import com.example.promosee.view.MainActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViewModel()
        setupAction()
    }

    private fun setupViewModel() {
        loginViewModel = ViewModelProvider(
            this,
            ViewModelFactory.getInstance(application)
        )[LoginViewModel::class.java]
    }

    private fun setupAction(){
        binding.btnLogin.setOnClickListener{
            val tvEmail = binding.editTextTextEmailAddress3.text.toString()
            val tvPassword = binding.editTextTextPassword.text.toString()
            val valid = binding.editTextTextEmailAddress3.error == null && binding.editTextTextPassword.error == null

            when{
                tvEmail.isEmpty() -> {
                    binding.editTextTextEmailAddress3.error = getString(R.string.email_empty)
                }
                tvPassword.isEmpty() -> {
                    binding.editTextTextPassword.error = getString(R.string.pass_empty)
                }
                valid -> {
                    binding.progressBar.visibility = View.VISIBLE
                    loginViewModel.setLoginInfo(tvEmail,tvPassword)
                    loginViewModel.login().observe(this){result ->
                        when(result){
                            is Result.Loading -> {
                                binding.progressBar.visibility = View.VISIBLE
                                binding.btnLogin.isEnabled = false
                            }
                            is Result.Success -> {
                                binding.btnLogin.isEnabled = true
                                binding.progressBar.visibility = View.GONE
                                val msg: String = getString(R.string.loginSuccess)
                                Log.e("test data", result.data.toString())
                                Toast.makeText(
                                    this@LoginActivity,
                                    msg,
                                    Toast.LENGTH_SHORT
                                ).show()
                                // intent ke halaman utama, dan meriset intent
                                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                                startActivity(intent)
                            }
                            is Result.Error -> {
                                binding.btnLogin.isEnabled = true
                                binding.progressBar.visibility = View.GONE
                                val msg: String = getString(R.string.loginFailed)
                                Toast.makeText(
                                    this@LoginActivity,
                                    msg,
                                    Toast.LENGTH_SHORT
                                ).show()

                            }
                        }
                    }

                }
            }
        }
    }


}