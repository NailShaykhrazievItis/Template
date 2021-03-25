package com.itis.template

import android.app.Application
import com.itis.template.di.component.AppComponent
import com.itis.template.di.component.DaggerAppComponent
import com.itis.template.di.component.WeatherSubComponent
import com.itis.template.di.module.AppModule
import com.itis.template.di.module.NetModule
import com.itis.template.di.module.WeatherModule
import moxy.MvpFacade

class ApplicationDelegate : Application() {

    override fun onCreate() {
        super.onCreate()
        MvpFacade.init()
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .netModule(NetModule())
            .build()
        weatherComponent = appComponent.weatherBuilder()
            .setModule(WeatherModule())
            .build()
    }

    companion object {
        lateinit var appComponent: AppComponent

        lateinit var weatherComponent: WeatherSubComponent
    }
}
