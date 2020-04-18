package com.itis.template.di.component

import com.itis.template.di.ActivityScope
import com.itis.template.di.module.WeatherModule
import com.itis.template.ui.MainActivity
import dagger.Component

@ActivityScope
@Component(dependencies = [AppComponent::class], modules = [WeatherModule::class])
interface MainComponent {

    fun inject(activity: MainActivity)

}
