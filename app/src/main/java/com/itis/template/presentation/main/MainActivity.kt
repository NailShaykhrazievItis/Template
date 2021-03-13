package com.itis.template.presentation.main

import android.location.Location
import android.os.Bundle
import android.view.View
import com.google.android.gms.location.LocationServices
import com.google.android.material.snackbar.Snackbar
import com.itis.template.R
import com.itis.template.data.LocationRepositoryImpl
import com.itis.template.data.WeatherRepositoryImpl
import com.itis.template.data.api.ApiFactory
import com.itis.template.domain.FindCityUseCase
import com.itis.template.utils.getErrorMessage
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class MainActivity : MvpAppCompatActivity(), MainView {

    @InjectPresenter
    lateinit var presenter: MainPresenter

    @ProvidePresenter
    fun providePresenter(): MainPresenter = initPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initListeners()
    }

    override fun checkLocationPermission() {
//        presenter.onLocationAccess()
//        presenter.onLocationDenied()
    }

    override fun showLoading() {
        progress.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progress.visibility = View.GONE
    }

    override fun consumerError(throwable: Throwable) {
        Snackbar.make(
            findViewById(android.R.id.content),
            throwable.getErrorMessage(resources),
            Snackbar.LENGTH_LONG
        ).show()
    }

    override fun showWeather(temperature: String) {
        tv_result.text = "Шаhaр Температурасы: $temperature"
        tv_result.visibility = View.VISIBLE
    }

    override fun showUserLocation(location: Location) {
        Snackbar.make(
            findViewById(android.R.id.content),
            "Lon: ${location.longitude}, Lat: ${location.latitude}",
            Snackbar.LENGTH_LONG
        ).show()
    }

    override fun navigateToLogin() {
    }

    private fun initPresenter() = MainPresenter(
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
        tv_hello.setOnClickListener { presenter.onHelloClick() }
        tv_location.setOnClickListener { presenter.onLocationClick() }
    }
}
