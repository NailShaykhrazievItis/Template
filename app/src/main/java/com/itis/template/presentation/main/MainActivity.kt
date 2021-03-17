package com.itis.template.presentation.main

import android.location.Location
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.location.LocationServices
import com.google.android.material.snackbar.Snackbar
import com.itis.template.R
import com.itis.template.data.LocationRepositoryImpl
import com.itis.template.data.WeatherRepositoryImpl
import com.itis.template.data.api.ApiFactory
import com.itis.template.domain.FindCityUseCase
import com.itis.template.presentation.ViewModelFactory
import com.itis.template.utils.getErrorMessage
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this, initFactory()).get(MainViewModel::class.java)

        initSubscribes()
        initListeners()
    }

    private fun initSubscribes() {
        with(viewModel) {
            progress().observe(this@MainActivity, {
                pb_main.isVisible = it
            })
            mainTemp().observe(this@MainActivity, {
                showWeather(it.toString())
            })
        }
    }

    fun showLoading() {
        pb_main.visibility = View.VISIBLE
    }

    fun hideLoading() {
        pb_main.visibility = View.GONE
    }

    fun consumerError(throwable: Throwable) {
        Snackbar.make(
            findViewById(android.R.id.content),
            throwable.getErrorMessage(resources),
            Snackbar.LENGTH_LONG
        ).show()
    }

    fun showWeather(temperature: String) {
        tv_result.text = "Шаhaр Температурасы: $temperature"
        tv_result.visibility = View.VISIBLE
    }

    fun showUserLocation(location: Location) {
        Snackbar.make(
            findViewById(android.R.id.content),
            "Lon: ${location.longitude}, Lat: ${location.latitude}",
            Snackbar.LENGTH_LONG
        ).show()
    }

    fun navigateToLogin() {
    }

    private fun initFactory() = ViewModelFactory(
        findCityUseCase = ApiFactory.weatherApi.let {
            WeatherRepositoryImpl(it).let {
                FindCityUseCase(it, Dispatchers.IO)
            }
        },
        locationRepository = LocationRepositoryImpl(
            client = LocationServices.getFusedLocationProviderClient(this)
        )
    )

    private fun initListeners() {
        tv_hello.setOnClickListener { viewModel.onHelloClick() }
        tv_location.setOnClickListener { viewModel.onLocationClick() }
    }
}
