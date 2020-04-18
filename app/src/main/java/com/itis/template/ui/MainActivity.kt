package com.itis.template.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.itis.template.App
import com.itis.template.R
import com.itis.template.repository.WeatherRepository
import kotlinx.coroutines.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), CoroutineScope by MainScope() {

    @Inject
    lateinit var repository: WeatherRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        App.mainComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        launch {
            val response = withContext(Dispatchers.IO) {
                repository.fetch("Moscow")
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
