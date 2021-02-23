package com.itis.template.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.itis.template.R
import com.itis.template.data.WeatherRepositoryImpl
import com.itis.template.data.api.ApiFactory
import com.itis.template.domain.FindCityUseCase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val api = ApiFactory.weatherApi

    @inject
    private lateinit var findCityUseCase: FindCityUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findCityUseCase = ApiFactory.weatherApi.let {
            WeatherRepositoryImpl(it).let {
                FindCityUseCase(it, Dispatchers.IO)
            }
        }

//        findCityUseCase = FindCityUseCase(WeatherRepositoryImpl(ApiFactory.weatherApi))

        tv_hello.setOnClickListener {
            onHelloClick()
        }
    }

    private fun onHelloClick() {
        lifecycleScope.launch {
            findCityUseCase.findWeatherInCity("Kazan").run {
                Snackbar.make(
                    findViewById(android.R.id.content),
                    "Шаhaр Температурасы: ${main.temp}",
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }
    }
}
