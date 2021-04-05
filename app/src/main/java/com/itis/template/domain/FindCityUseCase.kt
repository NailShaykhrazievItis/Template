package com.itis.template.domain

import com.itis.template.WeatherResponse
import com.itis.template.utils.subscribeOnIoObserveOnMain
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class FindCityUseCase(
    private val weatherRepository: WeatherRepository,
    private val context: CoroutineContext
) {

    suspend fun findWeatherInCitySusp(name: String): WeatherResponse =
        withContext(context) {
            weatherRepository.getWeatherSusp(name)
        }

    fun findWeatherInCity(name: String): Single<WeatherResponse> =
        weatherRepository.getWeather(name)
            .subscribeOnIoObserveOnMain()
}
