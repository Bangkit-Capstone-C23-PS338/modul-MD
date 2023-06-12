package com.example.promosee.view.influencer.mainInflu.ui.product

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.promosee.R
import com.example.promosee.databinding.ActivityProductFormBinding
import com.example.promosee.model.Result
import com.example.promosee.view.ViewModelFactory

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
        binding.backButton.setOnClickListener{ finish() }

        val intent = intent
        val formType = intent.getStringExtra("form_type") as String

        // untuk create data ( POST endpoint )
        if(formType == "create"){
            binding.deleteBtn.visibility = View.GONE
            binding.btnCont.weightSum = 1f
            binding.formbtn.setOnClickListener{
                val productName = binding.productNameEdt.text.toString()
                val description = binding.edtDesc.text.toString()
                val todo = binding.edtTodo.text.toString()
                val price = binding.edtPrice.text.toString()
                val sosmed = binding.getsosmed.text.toString()

                val valid = productName != "" && description != "" && todo != "" && sosmed != "" && price != ""
                when{
                    valid -> {
                        productViewModel.setProduct(productName,description,todo,sosmed, price)
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
        // untuk update data ( PUT endpoint )
        else{

            // update header
            binding.headerForm.setText(R.string.update_product)
            binding.formbtn.setText(R.string.update_product)
            binding.deleteBtn.visibility = View.VISIBLE
            // product id
            val productId = intent.getStringExtra("id") as String
            // GET data from database
            productViewModel.setProductId(productId)
            productViewModel.getProductItem().observe(this){result ->
                when(result){
                    is Result.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    is Result.Success -> {
                        binding.progressBar.visibility = View.GONE

                        // set edit text data
                        binding.productNameEdt.setText(result.data.name)
                        binding.edtDesc.setText(result.data.description)
                        binding.edtTodo.setText(result.data.toDo?.joinToString(separator = ","))
                        binding.getsosmed.setText(result.data.socialMediaType)
                        binding.edtPrice.setText(result.data.price.toString())
                    }
                    is Result.Error -> {
                        binding.progressBar.visibility = View.GONE
                        val msg: String = getString(R.string.fetchFailproduct)
                        Toast.makeText(
                            this,
                            msg,
                            Toast.LENGTH_SHORT
                        ).show()

                    }
                }
            }

            // update data
            binding.formbtn.setOnClickListener{

                val productName = binding.productNameEdt.text.toString()
                val description = binding.edtDesc.text.toString()
                val todo = binding.edtTodo.text.toString()
                val price = binding.edtPrice.text.toString()
                val sosmed = binding.getsosmed.text.toString()
                productViewModel.setProduct(productName,description,todo,sosmed, price)
                productViewModel.setProductId(productId)

                productViewModel.updateProductItem().observe(this){result ->
                    when(result){
                        is Result.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                        }
                        is Result.Success -> {
                            binding.progressBar.visibility = View.GONE
                            val msg: String = getString(R.string.productUpdated)
                            Toast.makeText(
                                this,
                                msg,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        is Result.Error -> {
                            binding.progressBar.visibility = View.GONE
                            val msg: String = getString(R.string.fetchFailproduct)
                            Toast.makeText(
                                this,
                                msg,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }

            binding.deleteBtn.setOnClickListener{
                productViewModel.setProductId(productId)
                productViewModel.deleteProductItem().observe(this){result ->
                    when(result){
                        is Result.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                        }
                        is Result.Success -> {
                            binding.progressBar.visibility = View.GONE
                            val msg: String = getString(R.string.deleteSuccess)
                            Toast.makeText(
                                this,
                                msg,
                                Toast.LENGTH_SHORT
                            ).show()
                            finish()
                        }
                        is Result.Error -> {
                            binding.progressBar.visibility = View.GONE
                            val msg: String = getString(R.string.failDelete)
                            Toast.makeText(
                                this,
                                msg,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
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