package com.itis.template

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Binder
import android.os.IBinder
import kotlin.random.Random

class TeaService : Service() {

    private val player: MediaPlayer = MediaPlayer()
    private lateinit var localBinder: LocalBinder

    inner class LocalBinder : Binder() {

        fun getService(): TeaService = this@TeaService
    }

    override fun onCreate() {
        super.onCreate()
        localBinder = LocalBinder()
    }

    override fun onBind(intent: Intent): IBinder = localBinder

    override fun onDestroy() {
        super.onDestroy()
        player.release()
    }

    fun calculateNumber(): Int = Random.nextInt(100)

    fun pause() = player.pause()
}
