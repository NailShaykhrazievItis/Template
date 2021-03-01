package com.itis.template.presentation.main

import android.location.Location
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.OneExecution
import moxy.viewstate.strategy.alias.Skip

@AddToEndSingle
interface MainView : MvpView {

    fun checkLocationPermission()

    fun showLoading()

    fun hideLoading()

    @Skip
    fun consumerError(throwable: Throwable)

    fun showWeather(temperature: String)

    fun showUserLocation(location: Location)

    @OneExecution
    fun navigateToLogin()
}
