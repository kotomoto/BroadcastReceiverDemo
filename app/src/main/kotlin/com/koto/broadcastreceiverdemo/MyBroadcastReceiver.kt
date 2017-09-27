package com.koto.broadcastreceiverdemo

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast


class MyBroadcastReceiver : BroadcastReceiver() {
    private val TAG = "MyBroadcastReceiver"

    override fun onReceive(context: Context, intent: Intent) {
        val sb = StringBuilder()
        sb.append("Action: " + intent.action + "\n")
        sb.append("URI: " + intent.toUri(Intent.URI_INTENT_SCHEME).toString() + "\n")
        val log = sb.toString()
        Log.d(TAG, log)
        Toast.makeText(context, log, Toast.LENGTH_LONG).show()
    }
}