package com.itis.template

import android.app.Application
import com.itis.template.di.Injector
import moxy.MvpFacade

class ApplicationDelegate : Application() {

    override fun onCreate() {
        super.onCreate()
        MvpFacade.init()
        Injector.init(this)
    }
}
