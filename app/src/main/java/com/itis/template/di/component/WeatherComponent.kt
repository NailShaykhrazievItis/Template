package com.itis.template.di.component

import com.itis.template.di.module.WeatherModule
import com.itis.template.presentation.main.MainActivity
import dagger.Subcomponent

@Subcomponent(modules = [WeatherModule::class])
interface WeatherComponent {

    fun inject(main: MainActivity)

    @Subcomponent.Builder
    interface Builder {

        fun setModule(weatherModule: WeatherModule): Builder

        fun build(): WeatherComponent
    }
}
