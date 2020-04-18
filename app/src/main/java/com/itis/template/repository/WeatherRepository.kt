package com.itis.template.repository

import com.itis.template.api.WeatherService
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val service: WeatherService
) {

    suspend fun fetch(name: String) = service.weatherByName(name)
}
