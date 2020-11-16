package com.itis.template

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var teaService: TeaService? = null
    private var songService: ISongAidlInterface? = null

    private val binderConnection = object : ServiceConnection {

        override fun onServiceConnected(className: ComponentName, service: IBinder) {
            Log.e("LOG_TAG", "MainActivity onServiceConnected")
            teaService = (service as? TeaService.LocalBinder)?.getService()
        }

        override fun onServiceDisconnected(className: ComponentName) {
            Log.e("LOG_TAG", "MainActivity onServiceDisconnected")
            teaService = null
        }
    }

    private val aidlConnection = object : ServiceConnection {

        override fun onServiceConnected(className: ComponentName, service: IBinder) {
            Log.e("LOG_TAG", "MainActivity onServiceConnected")
            songService = ISongAidlInterface.Stub.asInterface(service)
        }

        override fun onServiceDisconnected(className: ComponentName) {
            Log.e("LOG_TAG", "MainActivity onServiceDisconnected")
            songService = null
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        Intent(this, MyIntentService::class.java).also {
//            startService(it)
//
//            stopService(it)
//        }
//        MyIntentService.startActionBaz(this, "1", "2")

        tv_pause.setOnClickListener {
//            calculateInBinder()
            calculateInAidl()
        }
        tv_send.setOnClickListener {
            songService?.setCurrentSong(Song().apply {
                name = "Inner Space"
            })
            songService?.setCurrentSongFromBundle(Bundle().apply {
                putParcelable("key_song", Song().apply {
                    name = "Bundle song"
                })
            })
        }
        btn_play.setOnClickListener {
            songService?.play()
        }

        Log.e("MainActivity", "PID: ${android.os.Process.myPid()}")
    }

    override fun onStart() {
        super.onStart()
        val intent = Intent(this, SongService::class.java)
        bindService(intent, aidlConnection, Context.BIND_AUTO_CREATE)
    }

    override fun onStop() {
        super.onStop()
        songService?.let {
            unbindService(aidlConnection)
            songService = null
        }
    }

    private fun calculateInBinder() {
        teaService?.calculateNumber()?.also {
            Toast.makeText(this, "Number: $it", Toast.LENGTH_LONG).show()
        }
        teaService?.pause()
    }

    private fun calculateInAidl() {
        songService?.sum(2, 1)?.also {
            Toast.makeText(this, "Number: $it", Toast.LENGTH_LONG).show()
        }
    }
}
