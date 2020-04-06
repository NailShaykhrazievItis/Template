package com.itis.template.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.itis.template.App
import com.itis.template.R
import com.itis.template.api.WeatherService
import kotlinx.coroutines.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), CoroutineScope by MainScope() {

    @Inject
    lateinit var service: WeatherService

    override fun onCreate(savedInstanceState: Bundle?) {
        App.appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        launch {
            val response = withContext(Dispatchers.IO) {
                service.weatherByName("Moscow")
            }
            Snackbar.make(
                findViewById(android.R.id.content),
                response.main.toString(),
                Snackbar.LENGTH_INDEFINITE
            ).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        coroutineContext.cancelChildren()
    }
}
