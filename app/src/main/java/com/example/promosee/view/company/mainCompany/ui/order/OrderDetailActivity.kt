package com.example.promosee.view.company.mainCompany.ui.order

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.promosee.R
import com.example.promosee.databinding.ActivityOrderDetailBinding
import com.example.promosee.model.Result
import com.example.promosee.model.remote.reponse.OrderItem
import com.example.promosee.model.toLongDateFormat
import com.example.promosee.model.withCurrencyFormat
import com.example.promosee.view.ViewModelFactory
import com.example.promosee.view.company.mainCompany.MainCom
import com.example.promosee.view.company.mainCompany.ui.detailInfluencer.InfluencerDetailActivity

class OrderDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOrderDetailBinding
    private lateinit var orderDetailViewModel: OrderDetailViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViewModel()
        setupView()
    }

    private fun setupViewModel() {
        orderDetailViewModel = ViewModelProvider(
            this,
            ViewModelFactory.getInstance(application)
        )[OrderDetailViewModel::class.java]
    }
    private fun setupView() {
        val orderId = intent.getStringExtra(EXTRA_ORDER_ID).toString()
        orderDetailViewModel.getOrderDetail(orderId).observe(this){ result ->
            when(result){
                is Result.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Result.Success -> {
                    binding.progressBar.visibility = View.GONE
                    val order = result.data
                    Log.d("Cek Detail", order.toString())
                    setupAction(order)
                }
                is Result.Error -> {
                    binding.progressBar.visibility = View.GONE
                    val msg: String = getString(R.string.failed_to_order)
                    Toast.makeText(
                        applicationContext,
                        msg,
                        Toast.LENGTH_SHORT
                    ).show()
                    finish()
                }
            }
        }
    }

    private fun setupAction(order: OrderItem) {
        orderDetailViewModel.getUser().observe(this){ user ->
            Log.d("Cek akses", user.user_access)
            if (user.user_access == "business_owner"){
                binding.username.text = order.influencer_username
                binding.btnDetail.setOnClickListener {
                    val intentToDetail = Intent(this@OrderDetailActivity, InfluencerDetailActivity::class.java)
                    intentToDetail.putExtra("username", binding.username.text.toString())
                    startActivity(intentToDetail)
                }
            } else {
                binding.username.text = order.business_owner
                binding.labelInfluencer.text = getString(R.string.company)
                binding.btnDetail.visibility = View.GONE
            }
            when(order.status){
                "pending" -> {

                }
                "process" -> {

                }
                "waiting" -> {

                }
                "done" -> {

                }
                "failed" -> {

                }
            }
            binding.apply {
                backButton.setOnClickListener {
                    finish()
                }
                postingDate.text = order.posting_date.toLongDateFormat()
                orderCost.text = order.selected_product.price.toString().withCurrencyFormat()
                productType.text = order.product_type
                brief.text = order.brief
                promotionPackage.text = order.selected_product.name
                promotionMedia.text = order.selected_product.socialMediaType
                productName.text = order.product_name
                productLink.text = order.product_link
                senderAddress.text = order.sender_address
                receiverAddress.text = order.receiver_address
//                Log.d("Cek kurir", order.order_courier)
//                Log.d("Cek payment", order.payment_method)
//                when(order.order_courier){
//                    "JNE" -> {
//                        logoCourier.setImageResource(R.drawable.logo_jne)
//                    }
//                    "AnterAja" -> {
//                        logoCourier.setImageResource(R.drawable.logo_anter_aja)
//                    }
//                    "SiCepat" -> {
//                        logoCourier.setImageResource(R.drawable.logo_sicepat)
//                    }
//                }
//                when(order.payment_method){
//                    "BCA" -> {
//                        logoPayment.setImageResource(R.drawable.logo_bca)
//                    }
//                    "BNI" -> {
//                        logoPayment.setImageResource(R.drawable.logo_bni)
//                    }
//                    "BRI" -> {
//                        logoPayment.setImageResource(R.drawable.logo_bri)
//                    }
//                    "GoPay" -> {
//                        logoPayment.setImageResource(R.drawable.logo_gopay)
//                    }
//                    "DANA" -> {
//                        logoPayment.setImageResource(R.drawable.logo_dana)
//                    }
//                    "OVO" -> {
//                        logoPayment.setImageResource(R.drawable.logo_ovo)
//                    }
//                }
            }
        }
    }

    companion object{
        const val EXTRA_ORDER_ID = "extra_order_id"
    }

}