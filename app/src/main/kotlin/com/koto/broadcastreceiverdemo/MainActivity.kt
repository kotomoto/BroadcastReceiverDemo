package com.koto.broadcastreceiverdemo

import android.bluetooth.BluetoothAdapter
import android.content.BroadcastReceiver
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.support.v4.content.LocalBroadcastManager
import android.support.v7.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val CUSTOM_BROADCAST = "com.example.broadcast.MY_NOTIFICATION"

    private var receiver: BroadcastReceiver = MyBroadcastReceiver()
    private var localBroadcastManager: LocalBroadcastManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sendBroadcastButton = findViewById(R.id.sendLocalBroadcast)
        sendBroadcastButton.setOnClickListener {
            val intent = Intent()
            intent.action = CUSTOM_BROADCAST
            intent.putExtra("data", "Notice me senpai!")

            if (localBroadcastManager == null) {
                localBroadcastManager = LocalBroadcastManager.getInstance(this)
                localBroadcastManager?.registerReceiver(receiver, IntentFilter(CUSTOM_BROADCAST))
            }

            localBroadcastManager?.sendBroadcast(intent)
        }

        val filter = IntentFilter(Intent.ACTION_POWER_CONNECTED)
        filter.addAction(Intent.ACTION_POWER_DISCONNECTED)
        filter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED)
        this.registerReceiver(receiver, filter)
    }

    override fun onDestroy() {
        unregisterReceiver(receiver)
        localBroadcastManager?.unregisterReceiver(receiver)

        super.onDestroy()
    }
}
