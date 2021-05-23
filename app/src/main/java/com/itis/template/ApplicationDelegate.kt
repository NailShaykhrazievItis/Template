package com.itis.template

import android.app.Application
import com.itis.template.di.DaggerAppComponent
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import moxy.MvpFacade
import javax.inject.Inject

class ApplicationDelegate : Application(), HasAndroidInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder()
            .application(this)
            .build()
            .inject(this)
        MvpFacade.init()
    }

    override fun androidInjector() = dispatchingAndroidInjector
}
