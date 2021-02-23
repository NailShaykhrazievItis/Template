package com.itis.template.domain

import com.itis.template.WeatherResponse
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class FindCityUseCase(
    private val weatherRepository: WeatherRepository,
    private val context: CoroutineContext
) {

    suspend fun findWeatherInCity(name: String): WeatherResponse =
        withContext(context) {
            weatherRepository.getWeather(name)
        }
}
