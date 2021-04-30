package com.itis.template

import android.app.Application
import moxy.MvpFacade
import ru.terrakok.cicerone.Cicerone

class ApplicationDelegate: Application() {

    private val cicerone = Cicerone.create()
    val router get() = cicerone.router
    val navigatorHolder get() = cicerone.navigatorHolder

    override fun onCreate() {
        super.onCreate()
        MvpFacade.init()
        INSTANCE = this
    }

    companion object {
        internal lateinit var INSTANCE: ApplicationDelegate
            private set
    }
}
