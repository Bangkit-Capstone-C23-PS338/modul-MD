package com.example.promosee.adapter

import android.content.res.ColorStateList
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.promosee.R
import com.example.promosee.databinding.ItemOrderBinding
import com.example.promosee.model.local.preference.OrderModel
import com.example.promosee.model.remote.reponse.OrderItem
import com.example.promosee.model.toLongDateFormat

class OrderAdapter(private val dataList: List<OrderItem>) : RecyclerView.Adapter<OrderAdapter.ViewHolder>() {

    private var isTokenCompany = true

    private lateinit var onItemClickCallback: OnItemClickCallback

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
        val order = dataList[position]
        holder.binding.imgAvatar.setImageResource(R.drawable.iu)
        holder.binding.productName.text = order.product_name
        holder.binding.username.text = if (isTokenCompany) order.influencer_username else order.business_owner
        holder.binding.orderDate.text = order.posting_date.toLongDateFormat()
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
            onItemClickCallback.onItemClicked(dataList[position])
        }
    }

    override fun getItemCount(): Int = dataList.size

}