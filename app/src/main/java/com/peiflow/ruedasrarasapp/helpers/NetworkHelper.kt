package com.peiflow.ruedasrarasapp.helpers

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

class NetworkHelper {
    companion object{
        fun getNetworkAvailability(context:Context):Boolean{
            val cm: ConnectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
            return activeNetwork != null && activeNetwork.isConnected
        }
    }
}