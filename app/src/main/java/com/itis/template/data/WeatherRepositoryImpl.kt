package com.itis.template.data

import com.itis.template.WeatherResponse
import com.itis.template.data.api.WeatherApi
import com.itis.template.domain.WeatherRepository

class WeatherRepositoryImpl(
    private val weatherApi: WeatherApi
) : WeatherRepository {

    override suspend fun getWeatherSusp(
        cityName: String
    ): WeatherResponse {
        val result = weatherApi.getWeatherSusp(cityName)
//        dao.saveWeather(result)
        return result
    }

    override fun getWeather(
        cityName: String
    ) = weatherApi.getWeather(cityName)
}
