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
import com.example.promosee.model.remote.reponse.ProductsItemInfluencer

class ProductAdapter(private val products: List<ProductsItemInfluencer>): RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductAdapter.ViewHolder, position: Int) {
        holder.logo.setImageResource(R.drawable.full_insta_logo)
        holder.infoAcc.setImageResource(R.drawable.baseline_info_24)
        holder.title.text = products[position].name
        holder.desc.text = products[position].description

        var todo = ""
        products[position].toDo?.forEach {todo+="- $it\n"}
        holder.criteriaDetail.text = todo

        holder.projectPrice.text = products[position].price.toString()
    }

    override fun getItemCount(): Int {
        return products.size
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