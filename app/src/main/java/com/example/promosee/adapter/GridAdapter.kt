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
import com.example.promosee.model.remote.reponse.InfluencersItem
import com.example.promosee.model.withCurrencyFormat
import java.lang.Integer.MAX_VALUE
import java.lang.Math.min
import java.lang.StrictMath.max

class GridAdapter(private val dataList: List<InfluencersItem>) : RecyclerView.Adapter<GridAdapter.GridViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    interface OnItemClickCallback {
        fun onItemClicked(username: InfluencersItem)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_card_recom, parent, false)
        return GridViewHolder(view)
    }

    override fun onBindViewHolder(holder: GridViewHolder, position: Int) {
        val data = dataList[position]
        holder.avatarItem.setImageResource(R.drawable.nanami_mami)
        holder.nameItem.text = data.username

        // set min dan max follower
        val minFollower = min(min(data.igFollowers as Int,data.ttFollowers as Int),data.ytFollowers as Int)
        val maxFollower = max(max(data.igFollowers as Int,data.ttFollowers as Int),data.ytFollowers as Int)
        holder.Follower.text = if(minFollower == maxFollower){
            "$minFollower"
        } else {
            "$minFollower - $maxFollower"
        }

        //set min and max price
        var minPrice = MAX_VALUE
        var maxPrice = 0
        data.products?.forEach { data ->
            minPrice = min(minPrice,data?.price as Int)
        }
        data.products?.forEach { data ->
            maxPrice = max(maxPrice,data?.price as Int)
        }

        holder.price.text = if(minPrice == maxPrice){
            "${minPrice.toString().withCurrencyFormat()}"
        } else {
            "${minPrice.toString().withCurrencyFormat()} - ${maxPrice.toString().withCurrencyFormat()}"
        }
        holder.verifId.setImageResource(R.drawable.baseline_verified_24)
        holder.itemView.setOnClickListener{
            onItemClickCallback.onItemClicked(dataList[position])
        }
        holder.buttonBook.setOnClickListener{
            onItemClickCallback.onItemClicked(dataList[position])
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    inner class GridViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val avatarItem: ImageView = itemView.findViewById(R.id.avatar_card)
        val nameItem: TextView = itemView.findViewById(R.id.cardName)
        val Follower: TextView = itemView.findViewById(R.id.follower_Card)
        val price: TextView = itemView.findViewById(R.id.card_price)
        val verifId: ImageView = itemView.findViewById(R.id.verivied)
        val buttonBook: Button = itemView.findViewById(R.id.book_card_btn)
    }
}