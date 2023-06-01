package com.example.promosee.model

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