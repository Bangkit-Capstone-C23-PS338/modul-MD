package com.example.promosee.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.promosee.R
import com.example.promosee.model.local.preference.InfluencerModel

class GridAdapter(private val dataList: List<InfluencerModel>) : RecyclerView.Adapter<GridAdapter.GridViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_card_recom, parent, false)
        return GridViewHolder(view)
    }

    override fun onBindViewHolder(holder: GridViewHolder, position: Int) {
        val data = dataList[position]
        holder.avatarItem.setImageResource(R.drawable.iu)
        holder.verified.setImageResource(R.drawable.baseline_verified_24)
        holder.nameItem.text = data.username
        holder.Follower.text = "218.2K Followers"
        holder.price.text = "500ribu - 1juta"
        holder.button.isEnabled = true
        // Bind the data to the grid item layout components
        // holder.textView.text = data.text
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    inner class GridViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val avatarItem: ImageView = itemView.findViewById(R.id.avatar_card)
        val nameItem: TextView = itemView.findViewById(R.id.cardName)
        val Follower: TextView = itemView.findViewById(R.id.follower_Card)
        val price: TextView = itemView.findViewById(R.id.card_price)
        val verified: ImageView = itemView.findViewById(R.id.verivied)
        val button: Button = itemView.findViewById(R.id.btn_book)
    }
}