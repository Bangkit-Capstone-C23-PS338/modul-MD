package com.example.promosee.view.influencer.mainInflu.ui.product

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.promosee.R
import com.example.promosee.databinding.ActivityProductBinding
import com.example.promosee.databinding.ActivityProductFormBinding
import com.example.promosee.model.Result
import com.example.promosee.view.ViewModelFactory
import com.example.promosee.view.company.mainCompany.MainCom
import com.example.promosee.view.influencer.mainInflu.MainInfluencer

class ProductFormActivity : AppCompatActivity() {


    private lateinit var binding: ActivityProductFormBinding
    private lateinit var productViewModel: ProductViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductFormBinding.inflate(layoutInflater)

        setupViewmodel()
        setupAction()

        setContentView(binding.root)
    }

    private fun setupAction() {
        binding.backButton.setOnClickListener{finish()}

        binding.formbtn.setOnClickListener{
            val productName = binding.productNameEdt.text.toString()
            val description = binding.edtDesc.text.toString()
            val todo = binding.edtTodo.text.toString()
            val sosmed = binding.getsosmed.text.toString()

            val valid = productName != "" && description != "" && todo != "" && sosmed != ""
            when{
                valid -> {
                    productViewModel.setProduct(productName,description,todo,sosmed)
                    productViewModel.createProduct().observe(this){result ->
                        when(result){
                            is Result.Loading -> {
                                binding.progressBar.visibility = View.VISIBLE
                                binding.formbtn.isEnabled = false
                            }
                            is Result.Success -> {
                                binding.formbtn.isEnabled = true
                                binding.progressBar.visibility = View.GONE
                                val msg: String = getString(R.string.addprodictsuccess)
                                Log.e("test data", result.data.toString())
                                Toast.makeText(
                                    this,
                                    msg,
                                    Toast.LENGTH_SHORT
                                ).show()
                                finish()
                            }
                            is Result.Error -> {
                                binding.formbtn.isEnabled = true
                                binding.progressBar.visibility = View.GONE
                                val msg: String = getString(R.string.productFailadd)
                                Toast.makeText(
                                    this,
                                    msg,
                                    Toast.LENGTH_SHORT
                                ).show()

                            }
                        }
                    }
                }
                else -> {
                    val msg: String = getString(R.string.fieldEmpty)
                    Toast.makeText(
                        this,
                        msg,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }









    }

    private fun setupViewmodel() {
        productViewModel = ViewModelProvider(
            this,
            ViewModelFactory.getInstance(application)
        )[ProductViewModel::class.java]
    }
}