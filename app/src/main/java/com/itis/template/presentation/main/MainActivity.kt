package com.itis.template.presentation.main

import android.content.Intent
import android.location.Location
import android.os.Bundle
import android.view.View
import com.google.android.material.snackbar.Snackbar
import com.itis.template.R
import com.itis.template.di.Injector
import com.itis.template.presentation.auth.AuthActivity
import com.itis.template.utils.getErrorMessage
import kotlinx.android.synthetic.main.activity_main.*
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject

class MainActivity : MvpAppCompatActivity(), MainView {

    @Inject
    @InjectPresenter
    lateinit var presenter: MainPresenter

    @ProvidePresenter
    fun providePresenter(): MainPresenter = presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        Injector.plusWeatherComponent().inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initListeners()
    }

    override fun onDestroy() {
        Injector.clearWeatherComponent()
        super.onDestroy()
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

    private fun initListeners() {
        tv_hello.setOnClickListener { presenter.onHelloClick() }
        tv_location.setOnClickListener { presenter.onLocationClick() }
        btn_login.setOnClickListener {
            Intent(this, AuthActivity::class.java).also {
                startActivity(it)
            }
        }
    }
}
