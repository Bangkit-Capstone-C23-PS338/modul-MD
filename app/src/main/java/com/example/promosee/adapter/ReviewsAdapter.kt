package com.example.promosee.adapter

import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.promosee.R
import com.example.promosee.model.remote.reponse.ProductsItemInfluencer

class ReviewsAdapter: RecyclerView.Adapter<ReviewsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewsAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_review, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 5
    }

    override fun onBindViewHolder(holder: ReviewsAdapter.ViewHolder, position: Int) {
        holder.profilePic.setImageResource(R.drawable.profilepic)
        holder.companyName.setText(R.string.company_name)
        holder.postDate.setText(R.string.review_date)
        holder.commentDesc.setText(R.string.desc)
        holder.verifLogo.setImageResource(R.drawable.baseline_verified_24)
        holder.commentTitle.setText(R.string.komentar)
        holder.rateTitle.setText(R.string.rating_title)

        holder.star_1.setImageResource(R.drawable.baseline_star_24)
        holder.star_2.setImageResource(R.drawable.baseline_star_24)
        holder.star_3.setImageResource(R.drawable.baseline_star_24)
        holder.star_4.setImageResource(R.drawable.baseline_star_24)
        holder.star_5.setImageResource(R.drawable.baseline_star_24)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val profilePic : ImageView = itemView.findViewById(R.id.profilePic)
        val postDate : TextView = itemView.findViewById(R.id.review_date)
        val companyName : TextView = itemView.findViewById(R.id.company_name)
        val commentDesc : TextView = itemView.findViewById(R.id.comment_desc)
        val verifLogo : ImageView = itemView.findViewById(R.id.verif)
        val commentTitle : TextView = itemView.findViewById(R.id.comment_title)
        val rateTitle : TextView = itemView.findViewById(R.id.rating_title)
        val star_1 : ImageView = itemView.findViewById(R.id.star_1)
        val star_2 : ImageView = itemView.findViewById(R.id.star_2)
        val star_3 : ImageView = itemView.findViewById(R.id.star_3)
        val star_4 : ImageView = itemView.findViewById(R.id.star_4)
        val star_5 : ImageView = itemView.findViewById(R.id.star_5)
    }
}