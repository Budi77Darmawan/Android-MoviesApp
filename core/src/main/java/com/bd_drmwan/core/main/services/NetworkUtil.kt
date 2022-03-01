package com.bd_drmwan.core.main.services

import android.content.Context
import android.net.ConnectivityManager
import java.net.ConnectException

object NetworkUtil {
    fun isConnected(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = connectivityManager.activeNetworkInfo
        return netInfo != null && netInfo.isConnected
    }
}

class NetworkException : ConnectException() {
    override fun getLocalizedMessage(): String {
        return "No internet connection"
    }
}