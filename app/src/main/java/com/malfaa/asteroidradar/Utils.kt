package com.malfaa.asteroidradar

import android.annotation.SuppressLint
import android.os.Build
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("WeekBasedYear")
fun todayAsteroidsFormatter():String{
    val date = Calendar.getInstance().time
    val dateFormat = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        SimpleDateFormat(Constants.API_QUERY_DATE_FORMAT, Locale.ENGLISH)
    } else {
        SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)

    }
    return dateFormat.format(date)
}