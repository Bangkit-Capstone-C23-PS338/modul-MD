package com.example.promosee.adapter

import android.media.Image
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.promosee.R
import com.example.promosee.model.fromLongDateFormat
import com.example.promosee.model.remote.reponse.ProductsItemInfluencer
import com.example.promosee.model.remote.reponse.ReviewsItem
import com.example.promosee.model.reviewDate
import com.example.promosee.model.reviewDateFormat
import com.example.promosee.model.toLongDateFormat
import com.example.promosee.model.toShortDateFormat
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter


class ReviewsAdapter(private val reviews : List<ReviewsItem>): RecyclerView.Adapter<ReviewsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewsAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_review, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return reviews.size
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun convertDateFormat(inputDateTimeString: String): String {
        val inputFormat = "yyyy-MM-dd'T'HH:mm:ss.SSSSSSZ"
        val outputFormat = "dd MMMM yyyy"

        val inputDateTime = OffsetDateTime.parse(inputDateTimeString, DateTimeFormatter.ofPattern(inputFormat))
            .withOffsetSameInstant(ZoneOffset.UTC)
            .toLocalDateTime()

        return inputDateTime.format(DateTimeFormatter.ofPattern(outputFormat))
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ReviewsAdapter.ViewHolder, position: Int) {
        holder.profilePic.setImageResource(R.drawable.profilepic)
        holder.companyName.text = reviews[position].companyName
        holder.postDate.text = reviews[position].timeReviewed
        holder.commentDesc.text = reviews[position].comment
        holder.verifLogo.setImageResource(R.drawable.baseline_verified_24)
        holder.commentTitle.setText(R.string.komentar)
        holder.rateTitle.setText(R.string.rating_title)

        val rate = reviews[position].rating?.toDouble()
        if(rate != null){
            if(rate == 5.0){
                holder.star_1.setImageResource(R.drawable.baseline_star_24)
                holder.star_2.setImageResource(R.drawable.baseline_star_24)
                holder.star_3.setImageResource(R.drawable.baseline_star_24)
                holder.star_4.setImageResource(R.drawable.baseline_star_24)
                holder.star_5.setImageResource(R.drawable.baseline_star_24)
            }else if(rate < 5.0 && rate > 4.0){
                holder.star_1.setImageResource(R.drawable.baseline_star_half_24)
                holder.star_2.setImageResource(R.drawable.baseline_star_24)
                holder.star_3.setImageResource(R.drawable.baseline_star_24)
                holder.star_4.setImageResource(R.drawable.baseline_star_24)
                holder.star_5.setImageResource(R.drawable.baseline_star_24)
            }else if(rate == 4.0){
                holder.star_1.setImageResource(R.drawable.baseline_star_border_24)
                holder.star_2.setImageResource(R.drawable.baseline_star_24)
                holder.star_3.setImageResource(R.drawable.baseline_star_24)
                holder.star_4.setImageResource(R.drawable.baseline_star_24)
                holder.star_5.setImageResource(R.drawable.baseline_star_24)
            }else if(rate < 4.0 && rate > 3.0){
                holder.star_1.setImageResource(R.drawable.baseline_star_border_24)
                holder.star_2.setImageResource(R.drawable.baseline_star_half_24)
                holder.star_3.setImageResource(R.drawable.baseline_star_24)
                holder.star_4.setImageResource(R.drawable.baseline_star_24)
                holder.star_5.setImageResource(R.drawable.baseline_star_24)
            }else if(rate == 3.0){
                holder.star_1.setImageResource(R.drawable.baseline_star_border_24)
                holder.star_2.setImageResource(R.drawable.baseline_star_border_24)
                holder.star_3.setImageResource(R.drawable.baseline_star_24)
                holder.star_4.setImageResource(R.drawable.baseline_star_24)
                holder.star_5.setImageResource(R.drawable.baseline_star_24)
            }else if(rate < 3.0 && rate > 2.0){
                holder.star_1.setImageResource(R.drawable.baseline_star_border_24)
                holder.star_2.setImageResource(R.drawable.baseline_star_border_24)
                holder.star_3.setImageResource(R.drawable.baseline_star_half_24)
                holder.star_4.setImageResource(R.drawable.baseline_star_24)
                holder.star_5.setImageResource(R.drawable.baseline_star_24)
            }else if(rate == 2.0){
                holder.star_1.setImageResource(R.drawable.baseline_star_border_24)
                holder.star_2.setImageResource(R.drawable.baseline_star_border_24)
                holder.star_3.setImageResource(R.drawable.baseline_star_border_24)
                holder.star_4.setImageResource(R.drawable.baseline_star_24)
                holder.star_5.setImageResource(R.drawable.baseline_star_24)
            }else if(rate < 2.0 && rate > 1.0){
                holder.star_1.setImageResource(R.drawable.baseline_star_border_24)
                holder.star_2.setImageResource(R.drawable.baseline_star_border_24)
                holder.star_3.setImageResource(R.drawable.baseline_star_border_24)
                holder.star_4.setImageResource(R.drawable.baseline_star_half_24)
                holder.star_5.setImageResource(R.drawable.baseline_star_24)

            }else if(rate == 1.0){
                holder.star_1.setImageResource(R.drawable.baseline_star_border_24)
                holder.star_2.setImageResource(R.drawable.baseline_star_border_24)
                holder.star_3.setImageResource(R.drawable.baseline_star_border_24)
                holder.star_4.setImageResource(R.drawable.baseline_star_border_24)
                holder.star_5.setImageResource(R.drawable.baseline_star_24)
            }
        }

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