package com.itis.template.domain

import com.itis.template.WeatherResponse
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named
import kotlin.coroutines.CoroutineContext

class FindCityUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository,
    @Named("IO") private val context: CoroutineContext
) {

    suspend fun findWeatherInCity(name: String): WeatherResponse =
        withContext(context) {
            weatherRepository.getWeather(name)
        }
}
