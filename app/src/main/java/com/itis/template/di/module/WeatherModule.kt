package com.itis.template.di.module

import com.itis.template.data.WeatherRepositoryImpl
import com.itis.template.data.api.WeatherApi
import com.itis.template.domain.WeatherRepository
import dagger.Module
import dagger.Provides

@Module
class WeatherModule {

    @Provides
    fun provideWeatherRepository(
        weatherApi: WeatherApi
    ): WeatherRepository = WeatherRepositoryImpl(weatherApi)

//    @Provides
//    fun provideMainPresenter(
//        locationRepositoryImpl: LocationRepositoryImpl,
//        findCityUseCase: FindCityUseCase
//    ): MainPresenter = MainPresenter(findCityUseCase, locationRepositoryImpl)
}
