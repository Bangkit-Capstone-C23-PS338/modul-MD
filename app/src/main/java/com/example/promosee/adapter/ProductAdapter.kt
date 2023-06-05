package com.example.promosee.adapter

import android.content.Intent
import android.provider.Settings.Secure.getString
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.promosee.R
import com.example.promosee.databinding.ItemOrderBinding
import com.example.promosee.model.local.preference.InfluencerModel
import com.example.promosee.model.remote.reponse.ProductsItem
import com.example.promosee.model.remote.reponse.ProductsItemInfluencer
import com.example.promosee.view.company.mainCompany.ui.order.OrderActivity
import com.example.promosee.view.influencer.mainInflu.ui.product.ProductFormActivity
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.Locale

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
        holder.logo.setImageResource(R.drawable.full_insta_logo)
        holder.infoAcc.setImageResource(R.drawable.baseline_info_24)
        holder.title.text = products[position].name
        holder.desc.text = products[position].description

        var todo = ""
        products[position].toDo?.forEach {todo+="- $it\n"}
        holder.criteriaDetail.text = todo

        val price = products[position].price?.let { formatRupiah(it) }

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

    fun formatRupiah(amount: Int): String {
        // Create a NumberFormat instance for the Indonesian locale
        val rupiahFormat = NumberFormat.getCurrencyInstance(Locale("id", "ID"))

        // Set the currency symbol to "Rp "
        val decimalFormat = rupiahFormat as DecimalFormat
        decimalFormat.negativePrefix = "(Rp "
        decimalFormat.negativeSuffix = ")"

        // Format the amount
        var formattedAmount = rupiahFormat.format(amount.toLong())

        // Remove the currency symbol and replace it with "Rp "
        formattedAmount = formattedAmount.replace("IDR", "Rp ")

        // Remove any decimal places if present
        formattedAmount = formattedAmount.replace(".00", "")

        return formattedAmount
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