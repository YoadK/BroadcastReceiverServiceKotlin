package com.example.broadcastreceiverservicekotlin

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.widget.Toast

class MyService : Service() {

    private var myPlayer: MediaPlayer? = null

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onCreate() {
        Toast.makeText(this, "Service Created", Toast.LENGTH_LONG).show()
        myPlayer = MediaPlayer.create(this, R.raw.prison_break)
        myPlayer!!.isLooping = false // Set looping
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        Toast.makeText(this, "Service Started", Toast.LENGTH_LONG).show()
        myPlayer!!.start()
        return START_STICKY
    }

    override fun onDestroy() {
        Toast.makeText(this, "Service Stopped", Toast.LENGTH_LONG).show()
        myPlayer!!.stop()
    }

}
