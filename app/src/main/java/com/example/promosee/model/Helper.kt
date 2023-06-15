package com.example.promosee.model

import android.app.Activity
import android.widget.ImageView
import com.example.promosee.R
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Locale

fun String.toShortDateFormat(): String {
    val inputFormat = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
    val outputFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
    val date = inputFormat.parse(this)
    return outputFormat.format(date?.time)
}

fun String.toLongDateFormat(): String {
    val inputFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
    val outputFormat = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
    val date = inputFormat.parse(this)
    return outputFormat.format(date?.time)
}

fun String.fromLongDateFormat(): String {
    val inputFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    val outputFormat = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
    val date = inputFormat.parse(this)
    return outputFormat.format(date?.time)
}


fun String.fromReviewDateFormat(): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val outputFormat = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
    val date = inputFormat.parse(this)
    return outputFormat.format(date?.time)

}


fun String.withCurrencyFormat(): String {
    val myIndonesianLocale = Locale("in", "ID")
    val mCurrencyFormat = NumberFormat.getCurrencyInstance(myIndonesianLocale)
    var price = this.toDouble()
    if (price >=1000000000){
        price /= 1000000000
        return mCurrencyFormat.format(price) + "B"
    }
    else if (price >=1000000){
        price /= 1000000
        return mCurrencyFormat.format(price) + "M"
    }
    else if(price >= 1000){
        price /= 1000
        return mCurrencyFormat.format(price) + "K"
    }
    return mCurrencyFormat.format(price)
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