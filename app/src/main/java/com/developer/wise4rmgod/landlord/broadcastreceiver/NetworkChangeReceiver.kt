package com.developer.wise4rmgod.landlord.broadcastreceiver

import android.annotation.SuppressLint
import android.widget.Toast
import android.content.Intent
import android.content.BroadcastReceiver
import android.content.Context


open class NetworkChangeReceiver : BroadcastReceiver() {

   @SuppressLint("UnsafeProtectedBroadcastReceiver")
   override fun onReceive(context: Context, intent: Intent) {

        val status = NetworkUtil.getConnectivityStatusString(context)

        Toast.makeText(context, status, Toast.LENGTH_LONG).show()
    }
}