package com.example.promosee.model

import android.app.Activity
import android.os.Build
import android.widget.ImageView
import androidx.annotation.RequiresApi
import com.example.promosee.R
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
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

fun String.reviewDateFormat(): String {
    val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
    val simpleDateFormat2 = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
    val date = simpleDateFormat2.parse(this)
    return simpleDateFormat.format(date?.time)
}

fun String.reviewDate(): String {
    val inputFormat = "dd/MM/yyyy HH:mm:ss"
    val outputFormat = "dd/MM/yyyy"

    val simpleDateFormat = SimpleDateFormat(inputFormat, Locale.getDefault())
    val simpleDateFormat2 = SimpleDateFormat(outputFormat, Locale.getDefault())
    val date = simpleDateFormat2.parse(this)
    return simpleDateFormat.format(date?.time)
}


fun String.withCurrencyFormat(): String {
    val rupiahExchangeRate = 12495.95
    val euroExchangeRate = 0.88

    var priceOnDollar = this.toDouble() / rupiahExchangeRate

    var mCurrencyFormat = NumberFormat.getCurrencyInstance()
    val deviceLocale = Locale.getDefault().country
    when {
        deviceLocale.equals("ES") -> {
            priceOnDollar *= euroExchangeRate
        }
        deviceLocale.equals("ID") -> {
            priceOnDollar *= rupiahExchangeRate
        }
        else -> {
            mCurrencyFormat = NumberFormat.getCurrencyInstance(Locale.US)
        }
    }
    return mCurrencyFormat.format(priceOnDollar)
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