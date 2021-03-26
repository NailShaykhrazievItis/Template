package com.itis.template.di

import android.app.Application
import com.itis.template.di.component.AppComponent
import com.itis.template.di.component.DaggerAppComponent
import com.itis.template.di.component.WeatherComponent
import com.itis.template.di.module.WeatherModule

object Injector {

    private lateinit var appComponent: AppComponent

    private var weatherComponent: WeatherComponent? = null

    fun init(app: Application) {
        appComponent = DaggerAppComponent.builder()
            .application(app)
            .build()
    }

    fun plusWeatherComponent(): WeatherComponent = weatherComponent ?: appComponent.weatherComponent()
        .setModule(WeatherModule())
        .build().also {
            weatherComponent = it
        }

    fun authComponent() = appComponent.authComponent().create()

    fun clearWeatherComponent() {
        weatherComponent = null
    }
}
