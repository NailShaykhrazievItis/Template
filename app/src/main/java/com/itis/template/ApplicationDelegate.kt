package com.itis.template

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import moxy.MvpFacade

@HiltAndroidApp
class ApplicationDelegate : Application() {

    override fun onCreate() {
        super.onCreate()
        MvpFacade.init()
    }

}
