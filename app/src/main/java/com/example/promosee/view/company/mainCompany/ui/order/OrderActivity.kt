package com.example.promosee.view.company.mainCompany.ui.order

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.promosee.R
import com.example.promosee.databinding.ActivityOrderBinding
import com.example.promosee.model.Result
import com.example.promosee.model.local.preference.OrderModel
import com.example.promosee.model.remote.reponse.OrderItem
import com.example.promosee.model.remote.reponse.ProductsItemInfluencer
import com.example.promosee.model.toShortDateFormat
import com.example.promosee.view.ViewModelFactory
import com.example.promosee.view.company.mainCompany.ui.detailInfluencer.InfluencerDetailActivity
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointForward
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class OrderActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOrderBinding
    private lateinit var orderViewModel: OrderViewModel
    private var isValidated: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // bacn button
        binding.backButton.setOnClickListener{ finish() }
        setupViewModel()
        setupView()
        setupAction()
    }

    private fun setupViewModel() {
        orderViewModel = ViewModelProvider(
            this,
            ViewModelFactory.getInstance(application)
        )[OrderViewModel::class.java]
    }

    private fun setupView() {
        // get product from the detail page
        val product = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(EXTRA_PRODUCT, ProductsItemInfluencer::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_PRODUCT)
        }

        val username = intent.getStringExtra(EXTRA_USERNAME)


        binding.promotionMedia.text = product?.socialMediaType ?: ""
        binding.promotionPackage.text = product?.name ?: ""
        binding.price.text = product?.price.toString()
        binding.username.text = username
    }

    private fun setupAction() {
        binding.btnDetail.setOnClickListener {
            val intentToDetail = Intent(this@OrderActivity, InfluencerDetailActivity::class.java)
            intentToDetail.putExtra("username", binding.username.text.toString())
            startActivity(intentToDetail)
        }
        binding.btnDate.setOnClickListener {
            // Makes only dates from today forward selectable.
            val constraintsBuilder =
                CalendarConstraints.Builder()
                    .setValidator(DateValidatorPointForward.now())

            // Set default day into today, and input mode to text
            val picker = MaterialDatePicker.Builder.datePicker()
            .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
            .setInputMode(MaterialDatePicker.INPUT_MODE_TEXT)
            .setCalendarConstraints(constraintsBuilder.build()).build()


            // Show date picker
            picker.show(supportFragmentManager, "OrderActivity")

            picker.addOnPositiveButtonClickListener {
                val simpleDateFormat = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
                binding.postingDate.text = simpleDateFormat.format(Date(it).time)
            }
        }
        binding.btnOrder.setOnClickListener {
            runValidation()
            if (isValidated){
                val dateNew = binding.postingDate.text.toString().toShortDateFormat()
                val order = OrderItem(
                    influencer_username = binding.username.text.toString(),
//                    posting_date = dateNew,
                    product_name = binding.edtName.text.toString(),
                    product_link = binding.edtLink.text.toString(),
                    product_type = binding.edtType.text.toString(),
                    brief = binding.edtBrief.text.toString(),
                    order_courier = binding.listCourier.text.toString(),
                    payment_method = binding.listPayment.text.toString(),
                    selected_package = if (Build.VERSION.SDK_INT >= 33) {
                        intent.getParcelableExtra(EXTRA_PRODUCT, ProductsItemInfluencer::class.java)!!
                    } else {
                        @Suppress("DEPRECATION")
                        intent.getParcelableExtra(EXTRA_PRODUCT)!!
                    },
                    sender_address = binding.edtSenderAddress.text.toString(),
                    receiver_address = binding.edtReceiverAddress.text.toString(),
                )
                orderViewModel.createOrder(order, binding.username.text.toString()).observe(this){ result ->
                    when(result){
                        is Result.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                        }
                        is Result.Success -> {
                            binding.progressBar.visibility = View.GONE
                            val returnedOrder: OrderItem = result.data.order
                            Log.d("Order Returned:", returnedOrder.toString())
                        }
                        is Result.Error -> {
                            binding.progressBar.visibility = View.GONE
                            val msg: String = getString(R.string.failed_to_order)
                            Toast.makeText(
                                this@OrderActivity,
                                msg,
                                Toast.LENGTH_SHORT
                            ).show()

                        }
                    }
                }
            }

        }
    }

    private fun runValidation() {
        isValidated = true
        binding.apply {
            if(edtType.text?.isEmpty() as Boolean){
                textFieldProductType.error = getString(R.string.product_type_empty)
                isValidated = false
            } else{
                textFieldProductType.error = null
            }
            if(edtBrief.text?.isEmpty() as Boolean){
                textFieldBrief.error = getString(R.string.briefing_empty)
                isValidated = false
            } else{
                textFieldBrief.error = null
            }
            if(edtLink.text?.isEmpty() as Boolean){
                textFieldProductLink.error = getString(R.string.product_link_empty)
                isValidated = false
            } else{
                textFieldProductLink.error = null
            }
            if(edtSenderAddress.text?.isEmpty() as Boolean){
                textFieldSenderAddress.error = getString(R.string.sender_address_empty)
                isValidated = false
            } else{
                textFieldSenderAddress.error = null
            }
            if(edtReceiverAddress.text?.isEmpty() as Boolean){
                textFieldReceiverAddress.error = getString(R.string.receiver_address_empty)
                isValidated = false
            } else{
                textFieldReceiverAddress.error = null
            }
            if(edtName.text?.isEmpty() as Boolean){
                textFieldProductName.error = getString(R.string.product_name_empty)
                isValidated = false
            } else{
                textFieldProductName.error = null
            }
            if(binding.postingDate.text == getString(R.string.choose_a_date)){
                postingDateError.visibility = View.VISIBLE
                isValidated = false
            } else{
              postingDateError.visibility = View.GONE
            }
            if(listCourier.text?.isEmpty() as Boolean){
                dropdownCourier.error = getString(R.string.courier_service_empty)
                isValidated = false
            } else{
                dropdownCourier.error = null
            }
            if(listPayment.text?.isEmpty() as Boolean){
                dropdownPayment.error = getString(R.string.payment_method_empty)
                isValidated = false
            } else{
                dropdownPayment.error = null
            }
        }
    }

    companion object{
        const val EXTRA_PRODUCT = "extra_product"
        const val EXTRA_USERNAME = "extra_username"
    }
}