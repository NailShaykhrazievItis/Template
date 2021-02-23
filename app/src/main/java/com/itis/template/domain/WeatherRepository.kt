package com.itis.template.domain

import com.itis.template.WeatherResponse

interface WeatherRepository {

    suspend fun getWeather(cityName: String): WeatherResponse
}
