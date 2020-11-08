package com.itis.template

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tv_hello.setOnClickListener {
            showNotification()
        }
    }

    fun showNotification() {
        val channel = getNotificationChannel()
        val text =
            "Test text Test text Test text Test text Test text Test textdvdsvd  rfg sfg fds dsf hsfd hs"
        val builder = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationCompat.Builder(this, channel?.id ?: "")
        } else {
            NotificationCompat.Builder(this, "")
        }

        builder.setContentText(text)
            .setContentTitle("Title")
            .setStyle(NotificationCompat.BigTextStyle().bigText(text))
            .setSmallIcon(R.mipmap.ic_launcher)
            .setAutoCancel(true)
            .setContentIntent(pIntent())

        (getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager).run {
            notify(123, builder.build())
        }
    }

    private fun getNotificationChannel(): NotificationChannel? {
        val CHANNEL_ID = getString(R.string.channel_id)
        val notificationManager: NotificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager.getNotificationChannel(CHANNEL_ID) ?: run {
                val name = "name"
                val importance = NotificationManager.IMPORTANCE_DEFAULT
                val new = NotificationChannel(
                    CHANNEL_ID,
                    name,
                    importance
                ).apply {
                    description = "getString(R.string.channel_description)"
                    vibrationPattern = longArrayOf(23L, 34L, 454L)
                    lockscreenVisibility = Notification.VISIBILITY_SECRET
                    lightColor = Color.RED
                }
                notificationManager.createNotificationChannel(new)
                new
            }
        } else null
    }

    private fun pIntent() = PendingIntent.getActivity(
        this,
        34,
        Intent(this, MainActivity::class.java).apply {
            putExtra("EXTRA_NAME", "sd")
        },
        PendingIntent.FLAG_ONE_SHOT
    )
}
