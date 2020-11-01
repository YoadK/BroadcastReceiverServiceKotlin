package com.example.broadcastreceiverservicekotlin

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startBroadcastReceiver()
        buttonsClicks()
    }

    private fun startBroadcastReceiver() {
        val filter = IntentFilter(Intent.ACTION_POWER_CONNECTED)
        val receiver = BroadcastReceiverBattery()
        registerReceiver(receiver, filter)
    }

    private fun buttonsClicks() {
        btnStartService.setOnClickListener {
            startService(Intent(this, MyService::class.java))
        }

        btnStopService.setOnClickListener {
            stopService(Intent(this, MyService::class.java))
        }
    }

}
