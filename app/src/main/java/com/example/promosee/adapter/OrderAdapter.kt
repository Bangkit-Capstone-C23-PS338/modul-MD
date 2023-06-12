package com.example.promosee.adapter

import android.content.res.ColorStateList
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.promosee.R
import com.example.promosee.databinding.ItemOrderBinding
import com.example.promosee.model.fromLongDateFormat
import com.example.promosee.model.remote.reponse.OrderItem
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

class OrderAdapter(private val dataList: List<OrderItem>) : RecyclerView.Adapter<OrderAdapter.ViewHolder>() {

    private var isTokenCompany = true
    private lateinit var onItemClickCallback: OnItemClickCallback
    private var isHome = false

    val dateTimeFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")

    val sortedList: List<OrderItem> = if (isHome){
        dataList.sortedByDescending {
            //inputFormat.parse(it.order_date.toString())
            LocalDate.parse(it.order_date.toString(), dateTimeFormatter)
        }
    }else{
        dataList.sortedByDescending {
            //inputFormat.parse(it.order_date.toString())
            LocalDate.parse(it.order_date.toString(), dateTimeFormatter)
        }.take(5)
    }

    fun setHome(boolean: Boolean){
        isHome = boolean
    }

    fun checkTokenCompany(check: Boolean){
        isTokenCompany = check
    }

    interface OnItemClickCallback {
        fun onItemClicked(order: OrderItem)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    class ViewHolder(var binding: ItemOrderBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemOrderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val order = sortedList[position]
        Log.d("Sorted:", sortedList.toString())
        holder.binding.imgAvatar.setImageResource(R.drawable.iu)
        holder.binding.productName.text = order.product_name
        holder.binding.username.text = if (isTokenCompany) order.influencer_username else order.business_owner
        holder.binding.orderDate.text = order.order_date.toString().fromLongDateFormat()
        when(order.status){
            "pending" -> {
                holder.binding.statusChip.chipBackgroundColor = ColorStateList.valueOf(Color.parseColor("#423E3C"))
                holder.binding.statusChip.setTextColor(ColorStateList.valueOf(Color.parseColor("#F6E8E3")))
                holder.binding.statusChip.setText(R.string.pending)
            }
            "processing" -> {
                holder.binding.statusChip.chipBackgroundColor = ColorStateList.valueOf(Color.parseColor("#FFE08D"))
                holder.binding.statusChip.setTextColor(ColorStateList.valueOf(Color.parseColor("#A77800")))
                holder.binding.statusChip.setText(R.string.processing)
            }
            "waiting" -> {
                holder.binding.statusChip.chipBackgroundColor = ColorStateList.valueOf(Color.parseColor("#FFAD7E"))
                holder.binding.statusChip.setTextColor(ColorStateList.valueOf(Color.parseColor("#A73200")))
                holder.binding.statusChip.setText(R.string.waiting_for_confirmation)
            }
            "done" -> {
                holder.binding.statusChip.chipBackgroundColor = ColorStateList.valueOf(Color.parseColor("#B5DEC9"))
                holder.binding.statusChip.setTextColor(ColorStateList.valueOf(Color.parseColor("#1E8776")))
                holder.binding.statusChip.setText(R.string.done)
            }
            "failed" -> {
                holder.binding.statusChip.chipBackgroundColor = ColorStateList.valueOf(Color.parseColor("#FF7D7D"))
                holder.binding.statusChip.setTextColor(ColorStateList.valueOf(Color.parseColor("#E31B1B")))
                holder.binding.statusChip.setText(R.string.failed)
            }
        }
        holder.itemView.setOnClickListener{
            onItemClickCallback.onItemClicked(sortedList[position])
        }
    }

    override fun getItemCount(): Int = sortedList.size

}