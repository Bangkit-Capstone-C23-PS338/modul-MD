package com.example.promosee.model

import android.app.Activity
import android.widget.ImageView
import com.example.promosee.R
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Locale

fun String.toShortDateFormat(): String {
    val simpleDateFormat = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
    val simpleDateFormat2 = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
    val date = simpleDateFormat.parse(this)
    return simpleDateFormat2.format(date?.time)
}

fun String.toLongDateFormat(): String {
    val simpleDateFormat = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
    val simpleDateFormat2 = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
    val date = simpleDateFormat2.parse(this)
    return simpleDateFormat.format(date?.time)
}

fun String.withNumberingFormat(): String {
    return NumberFormat.getNumberInstance().format(this.toDouble())
}

fun setStarRating(rate: Double, activity: Activity){
    if(rate == 5.0){
        activity.findViewById<ImageView>(R.id.star_1).setImageResource(R.drawable.baseline_star_24)
        activity.findViewById<ImageView>(R.id.star_2).setImageResource(R.drawable.baseline_star_24)
        activity.findViewById<ImageView>(R.id.star_3).setImageResource(R.drawable.baseline_star_24)
        activity.findViewById<ImageView>(R.id.star_4).setImageResource(R.drawable.baseline_star_24)
        activity.findViewById<ImageView>(R.id.star_5).setImageResource(R.drawable.baseline_star_24)

    }else if(rate < 5.0 && rate > 4.0){
        activity.findViewById<ImageView>(R.id.star_1).setImageResource(R.drawable.baseline_star_half_24)
        activity.findViewById<ImageView>(R.id.star_2).setImageResource(R.drawable.baseline_star_24)
        activity.findViewById<ImageView>(R.id.star_3).setImageResource(R.drawable.baseline_star_24)
        activity.findViewById<ImageView>(R.id.star_4).setImageResource(R.drawable.baseline_star_24)
        activity.findViewById<ImageView>(R.id.star_5).setImageResource(R.drawable.baseline_star_24)

    }else if(rate == 4.0){
        activity.findViewById<ImageView>(R.id.star_1).setImageResource(R.drawable.baseline_star_border_24)
        activity.findViewById<ImageView>(R.id.star_2).setImageResource(R.drawable.baseline_star_24)
        activity.findViewById<ImageView>(R.id.star_3).setImageResource(R.drawable.baseline_star_24)
        activity.findViewById<ImageView>(R.id.star_4).setImageResource(R.drawable.baseline_star_24)
        activity.findViewById<ImageView>(R.id.star_5).setImageResource(R.drawable.baseline_star_24)

    }else if(rate < 4.0 && rate > 3.0){
        activity.findViewById<ImageView>(R.id.star_1).setImageResource(R.drawable.baseline_star_border_24)
        activity.findViewById<ImageView>(R.id.star_2).setImageResource(R.drawable.baseline_star_half_24)
        activity.findViewById<ImageView>(R.id.star_3).setImageResource(R.drawable.baseline_star_24)
        activity.findViewById<ImageView>(R.id.star_4).setImageResource(R.drawable.baseline_star_24)
        activity.findViewById<ImageView>(R.id.star_5).setImageResource(R.drawable.baseline_star_24)

    }else if(rate == 3.0){
        activity.findViewById<ImageView>(R.id.star_1).setImageResource(R.drawable.baseline_star_border_24)
        activity.findViewById<ImageView>(R.id.star_2).setImageResource(R.drawable.baseline_star_border_24)
        activity.findViewById<ImageView>(R.id.star_3).setImageResource(R.drawable.baseline_star_24)
        activity.findViewById<ImageView>(R.id.star_4).setImageResource(R.drawable.baseline_star_24)
        activity.findViewById<ImageView>(R.id.star_5).setImageResource(R.drawable.baseline_star_24)

    }else if(rate < 3.0 && rate > 2.0){
        activity.findViewById<ImageView>(R.id.star_1).setImageResource(R.drawable.baseline_star_border_24)
        activity.findViewById<ImageView>(R.id.star_2).setImageResource(R.drawable.baseline_star_border_24)
        activity.findViewById<ImageView>(R.id.star_3).setImageResource(R.drawable.baseline_star_half_24)
        activity.findViewById<ImageView>(R.id.star_4).setImageResource(R.drawable.baseline_star_24)
        activity.findViewById<ImageView>(R.id.star_5).setImageResource(R.drawable.baseline_star_24)

    }else if(rate == 2.0){
        activity.findViewById<ImageView>(R.id.star_1).setImageResource(R.drawable.baseline_star_border_24)
        activity.findViewById<ImageView>(R.id.star_2).setImageResource(R.drawable.baseline_star_border_24)
        activity.findViewById<ImageView>(R.id.star_3).setImageResource(R.drawable.baseline_star_border_24)
        activity.findViewById<ImageView>(R.id.star_4).setImageResource(R.drawable.baseline_star_24)
        activity.findViewById<ImageView>(R.id.star_5).setImageResource(R.drawable.baseline_star_24)

    }else if(rate < 2.0 && rate > 1.0){
        activity.findViewById<ImageView>(R.id.star_1).setImageResource(R.drawable.baseline_star_border_24)
        activity.findViewById<ImageView>(R.id.star_2).setImageResource(R.drawable.baseline_star_border_24)
        activity.findViewById<ImageView>(R.id.star_3).setImageResource(R.drawable.baseline_star_border_24)
        activity.findViewById<ImageView>(R.id.star_4).setImageResource(R.drawable.baseline_star_half_24)
        activity.findViewById<ImageView>(R.id.star_5).setImageResource(R.drawable.baseline_star_24)

    }else if(rate == 1.0){
        activity.findViewById<ImageView>(R.id.star_1).setImageResource(R.drawable.baseline_star_border_24)
        activity.findViewById<ImageView>(R.id.star_2).setImageResource(R.drawable.baseline_star_border_24)
        activity.findViewById<ImageView>(R.id.star_3).setImageResource(R.drawable.baseline_star_border_24)
        activity.findViewById<ImageView>(R.id.star_4).setImageResource(R.drawable.baseline_star_border_24)
        activity.findViewById<ImageView>(R.id.star_5).setImageResource(R.drawable.baseline_star_24)

    }
}