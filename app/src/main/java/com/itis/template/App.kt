package com.itis.template

import android.app.Application
import com.itis.template.di.component.AppComponent
import com.itis.template.di.component.DaggerAppComponent
import com.itis.template.di.component.DaggerMainComponent
import com.itis.template.di.component.MainComponent
import com.itis.template.di.module.AppModule
import com.itis.template.di.module.NetModule
import com.itis.template.di.module.ServiceModule
import com.itis.template.di.module.WeatherModule

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .netModule(NetModule())
            .serviceModule(ServiceModule())
            .build()

        mainComponent = DaggerMainComponent.builder()
            .appComponent(appComponent)
            .weatherModule(WeatherModule())
            .build()
    }

    companion object {

        lateinit var appComponent: AppComponent
        lateinit var mainComponent: MainComponent
    }
}
