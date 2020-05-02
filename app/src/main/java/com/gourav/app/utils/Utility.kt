package com.gourav.app.utils

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*


fun getFormatedDate(serverDate: String):String {

    var formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
    var postedDate = LocalDateTime.parse(serverDate, formatter)

    var currentYearDateFormat = DateTimeFormatter.ofPattern("d MMMM, HH:mm")
    var otherYearDateFormate = DateTimeFormatter.ofPattern("d MMMM yyyy, HH:mm")
    val today = Calendar.getInstance()
    val currentYear = today.get(Calendar.YEAR);

    if (currentYear.equals(postedDate.year))
        return LocalDateTime.parse(serverDate, formatter).format(currentYearDateFormat);
    else
        return LocalDateTime.parse(serverDate, formatter).format(otherYearDateFormate);
}


fun isOnline(context: Application?): Boolean {
    if (context == null) return false
    val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            ?: return false
    val netInfo = cm.activeNetworkInfo
    return netInfo != null && netInfo.isConnected
}


