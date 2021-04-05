package com.itis.template.domain

import com.itis.template.WeatherResponse
import io.reactivex.rxjava3.core.Single

interface WeatherRepository {

    suspend fun getWeatherSusp(cityName: String): WeatherResponse

    fun getWeather(cityName: String): Single<WeatherResponse>
}
