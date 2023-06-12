package com.example.promosee.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.promosee.R
import com.example.promosee.model.remote.reponse.ProductsItemInfluencer
import com.example.promosee.model.withCurrencyFormat
import com.example.promosee.view.company.mainCompany.ui.order.OrderActivity
import com.example.promosee.view.influencer.mainInflu.ui.product.ProductFormActivity

class ProductAdapter(
    private val products: List<ProductsItemInfluencer>,
    private val userType: String
): RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    private var username: String = ""
    fun setUsername(newUsername: String){
        username = newUsername
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductAdapter.ViewHolder, position: Int) {
        if(products[position].socialMediaType == "Tiktok"){
            holder.logo.setImageResource(R.drawable.tiktok_logo)
        }else if(products[position].socialMediaType == "Instagram"){
            holder.logo.setImageResource(R.drawable.full_insta_logo)
        }else{
            holder.logo.setImageResource(R.drawable.youtubelogo)
        }
        holder.infoAcc.setImageResource(R.drawable.baseline_info_24)
        holder.title.text = products[position].name
        holder.desc.text = products[position].description

        var todo = ""
        products[position].toDo?.forEach {todo+="- $it\n"}
        holder.criteriaDetail.text = todo

        val price = products[position].price?.toString()?.withCurrencyFormat()

        holder.projectPrice.text = price.toString()

        if(userType == "bussiness"){
            holder.checkBtn.setOnClickListener {
                val intentToOrder = Intent(holder.itemView.context, OrderActivity::class.java)
                intentToOrder.putExtra(OrderActivity.EXTRA_PRODUCT, products[position])
                intentToOrder.putExtra(OrderActivity.EXTRA_USERNAME, username)
                holder.itemView.context.startActivity(intentToOrder)
            }
        }else{
            holder.checkBtn.setText(R.string.edit)
            val id = products[position].productId.toString()
            holder.checkBtn.setOnClickListener {
                val moveIntent = Intent(holder.itemView.context, ProductFormActivity::class.java)
                moveIntent.putExtra("form_type", "update")
                moveIntent.putExtra("id", id)
                holder.itemView.context.startActivity(moveIntent)
            }
        }


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