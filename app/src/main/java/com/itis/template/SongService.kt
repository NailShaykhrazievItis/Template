package com.itis.template

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import kotlin.random.Random

class SongService : Service() {

    private var mediaPlayer: MediaPlayer = MediaPlayer()

    private val serviceSong = Song().apply {
        name = "ServiceSong"
    }

    private val mBinder: ISongAidlInterface.Stub = object : ISongAidlInterface.Stub() {
        val pid: Int
            get() = android.os.Process.myPid()

        override fun sum(a: Int, b: Int): Int {
            Log.e("SongService", "PID: $pid")
            return this@SongService.sumOfMultiply(a, b)
        }

        override fun play() {
            if (Random.nextBoolean()) playLocaleMusic() else playRemoteMusic()
        }

        override fun pause() = this@SongService.pause()

        override fun getSong(): Song = serviceSong

        override fun setCurrentSong(song: Song?) {
            showSong(song)
        }

        override fun setCurrentSongFromBundle(bundle: Bundle) {
            with(bundle) {
                classLoader = this@SongService.classLoader
                getParcelable<Song>("key_song").also {
                    showSong(it)
                }
            }
        }
    }

    override fun onBind(intent: Intent): IBinder = mBinder

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release()
    }

    private fun sumOfMultiply(a: Int, b: Int) = a * 2 + b * 3

    private fun pause() = mediaPlayer.pause()

    private fun showSong(song: Song?) {
        Log.e("Song from activity", song?.name.toString())
    }

    /**
     * When you call create, file is already prepared and don't need call prepare() method
     */
    private fun playLocaleMusic() {
        if (mediaPlayer.isPlaying) mediaPlayer.stop()
        mediaPlayer = MediaPlayer.create(applicationContext, R.raw.promise)
        mediaPlayer.run {
            start()
            setOnCompletionListener {
                stop() // or call next() for change track
            }
        }
    }

    /**
     * If you use setDataSource() then need call prepare() method before calling start()
     */
    private fun playRemoteMusic() {
        if (mediaPlayer.isPlaying) mediaPlayer.stop()
        mediaPlayer.run {
            setDataSource("https://zaycev.net/musicset/dl/9bc817a0041fd20e8a6b6d3e13bb84db/8633041.json?spa=false")
//            setDataSource("https://zaycev.net/musicset/dl/c75625f883500bcf31854123b14d199c/17405061.json?spa=false")
            prepareAsync()
            setOnPreparedListener {
                start()
            }
        }
    }
}
