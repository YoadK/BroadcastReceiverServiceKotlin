package com.example.broadcastreceiverservicekotlin

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat

// BroadcastReceiver are check if the phone are charged
class BroadcastReceiverBattery : BroadcastReceiver() {

    private var notificationManager: NotificationManager? = null
    private var builder: NotificationCompat.Builder? = null
    private var pendingIntent: PendingIntent? = null

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onReceive(context: Context, intent: Intent) {
        when (intent.action!!) {
            Intent.ACTION_POWER_CONNECTED -> {
                val NOTIFY_ID = 1 // ID of notification
                val id = "1" // default_channel_id
                val title = "MyBroadcastReceiver" // Default Channel
                if (notificationManager == null) {
                    notificationManager =
                        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    val importance = NotificationManager.IMPORTANCE_HIGH
                    var mChannel = notificationManager!!.getNotificationChannel(id)
                    if (mChannel == null) {
                        mChannel = NotificationChannel(id, title, importance)
                        mChannel.enableVibration(true)
                        mChannel.vibrationPattern =
                            longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 400)
                        notificationManager!!.createNotificationChannel(mChannel)
                    }
                    getBuilder(context)
                } else {
                    getBuilder(context)
                }
                val notification = builder!!.build()
                notificationManager!!.notify(NOTIFY_ID, notification)
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun getBuilder(context: Context) {
        builder = NotificationCompat.Builder(context, "1")
        val intent = Intent(context, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
        pendingIntent = PendingIntent.getActivity(context, 1, intent, 0)
        builder!!.setContentTitle("MyBroadcastReceiver")
            .setContentText("MyBroadcastReceiver") // required
            .setSmallIcon(R.mipmap.ic_launcher) // required
            .setDefaults(Notification.DEFAULT_ALL)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
            .setTicker("MyBroadcastReceiver")
            .setVibrate(longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 400))
            .priority = NotificationManager.IMPORTANCE_HIGH
    }

}
