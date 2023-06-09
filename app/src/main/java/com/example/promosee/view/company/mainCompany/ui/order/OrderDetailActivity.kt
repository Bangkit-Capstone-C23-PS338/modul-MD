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
import com.example.promosee.model.remote.request.UpdateOrderRequest
import com.example.promosee.model.toLongDateFormat
import com.example.promosee.model.withCurrencyFormat
import com.example.promosee.view.ViewModelFactory
import com.example.promosee.view.company.mainCompany.MainCom
import com.example.promosee.view.company.mainCompany.ui.detailInfluencer.InfluencerDetailActivity
import com.example.promosee.view.influencer.mainInflu.MainInfluencer

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
                    val msg: String = getString(R.string.failed_to_fetch_data)
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
            // change button visibility and behavior depend on the order status
            val intentToMainCom = Intent(this@OrderDetailActivity, MainCom::class.java)
            val intentToMainInfluencer = Intent(this@OrderDetailActivity, MainInfluencer::class.java)
            when(order.status){
                "pending" -> {
                    if(user.user_access == "influencer"){
                        binding.apply {
                            bottomAppBar.visibility = View.VISIBLE
                            btnActionLeft.setOnClickListener {
                                updateOrder(status = "failed", order_id = order.order_id)
                                startActivity(intentToMainInfluencer)
                            }
                            btnActionRight.setOnClickListener {
                                updateOrder(status = "processing", order_id = order.order_id)
                                startActivity(intentToMainInfluencer)
                            }
                        }

                    }
                }
                "processing" -> {
                    if(user.user_access == "influencer"){
                        binding.apply {
                            labelPromotion.visibility = View.VISIBLE
                            labelPromotionLink.visibility = View.VISIBLE
                            textFieldPromotionLink.visibility = View.VISIBLE

                            bottomAppBar.visibility = View.VISIBLE
                            btnActionLeft.visibility = View.INVISIBLE
                            btnActionRight.text = getString(R.string.submit)
                            btnActionRight.setOnClickListener {
                                if(edtPromotionLink.text?.isEmpty() as Boolean){
                                    textFieldPromotionLink.error = "Content link must be filled"
                                } else{
                                    textFieldPromotionLink.error = null
                                    updateOrder(
                                        content_link = edtPromotionLink.text.toString(),
                                        status = "waiting",
                                        order_id = order.order_id
                                    )
                                    startActivity(intentToMainInfluencer)
                                }
                            }
                        }
                    }
                }
                "waiting" -> {
                    binding.apply {
                        labelPromotion.visibility = View.VISIBLE
                        labelPromotionLink.visibility = View.VISIBLE
                        promotionLink.visibility = View.VISIBLE
                        promotionLink.text = order.content_link

                        if(user.user_access == "business_owner"){
                            bottomAppBar.visibility = View.VISIBLE
                            btnActionLeft.visibility = View.INVISIBLE
                            btnActionRight.text = getString(R.string.finish)
                            btnActionRight.setOnClickListener {
                                if(edtPromotionLink.text?.isEmpty() as Boolean){
                                    textFieldPromotionLink.error = "Content link must be filled"
                                } else{
                                    textFieldPromotionLink.error = null
                                    updateOrder(
                                        content_link = promotionLink.text.toString(),
                                        status = "done",
                                        order_id = order.order_id
                                    )
                                    startActivity(intentToMainCom)
                                }
                            }
                        }
                    }

                }
                "done" -> {
                    binding.apply {
                        labelPromotion.visibility = View.VISIBLE
                        labelPromotionLink.visibility = View.VISIBLE
                        promotionLink.visibility = View.VISIBLE
                        promotionLink.text = order.content_link
                    }
                }
            }

            // apply all order detail to view and change courier and payment method image depend on the order detail
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
                when(order.order_courier){
                    "JNE" -> {
                        logoCourier.setImageResource(R.drawable.logo_jne)
                    }
                    "AnterAja" -> {
                        logoCourier.setImageResource(R.drawable.logo_anter_aja)
                    }
                    "SiCepat" -> {
                        logoCourier.setImageResource(R.drawable.logo_sicepat)
                    }
                }
                when(order.payment_method){
                    "BCA" -> {
                        logoPayment.setImageResource(R.drawable.logo_bca)
                    }
                    "BNI" -> {
                        logoPayment.setImageResource(R.drawable.logo_bni)
                    }
                    "BRI" -> {
                        logoPayment.setImageResource(R.drawable.logo_bri)
                    }
                    "GoPay" -> {
                        logoPayment.setImageResource(R.drawable.logo_gopay)
                    }
                    "DANA" -> {
                        logoPayment.setImageResource(R.drawable.logo_dana)
                    }
                    "OVO" -> {
                        logoPayment.setImageResource(R.drawable.logo_ovo)
                    }
                }
            }
        }
    }

    fun updateOrder(content_link:String = "", status: String, order_id: String){
        val update_data = UpdateOrderRequest(content_link, status)
        orderDetailViewModel.updateOrder(update_data, order_id).observe(this@OrderDetailActivity){ result ->
            when(result){
                is Result.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Result.Success -> {
                    binding.progressBar.visibility = View.GONE
                    Log.d("Cek Update", result.data.message.toString())
                }
                is Result.Error -> {
                    binding.progressBar.visibility = View.GONE
                    val msg: String = getString(R.string.failed_to_update)
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

    companion object{
        const val EXTRA_ORDER_ID = "extra_order_id"
    }

}