package com.example.promosee.adapter

import android.provider.Settings.Secure.getString
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.promosee.R
import com.example.promosee.databinding.ItemOrderBinding

class ProductAdapter(): RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductAdapter.ViewHolder, position: Int) {
        holder.logo.setImageResource(R.drawable.full_insta_logo)
        holder.infoAcc.setImageResource(R.drawable.baseline_info_24)
        holder.title.setText(R.string.product_name)
        holder.desc.setText(R.string.desc)
        holder.criteriaDetail.setText(R.string.todo)
        holder.projectPrice.setText(R.string.product_price)
    }

    override fun getItemCount(): Int {
        return 5
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val logo: ImageView = itemView.findViewById(R.id.sosmed_logo)
        val title: TextView = itemView.findViewById(R.id.paket_title)
        val desc: TextView = itemView.findViewById(R.id.desc_sosmed)
        val criteriaDetail: TextView = itemView.findViewById(R.id.criteria)
        val projectPrice: TextView = itemView.findViewById(R.id.project_price)
        val checkBtn: Button = itemView.findViewById(R.id.checkBtn)
        val infoAcc: ImageView = itemView.findViewById(R.id.infoAcc)
    }


}