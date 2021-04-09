package com.itis.template.domain

import com.itis.template.WeatherResponse
import com.itis.template.utils.subscribeOnIoObserveOnMain
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.subjects.ReplaySubject
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class FindCityUseCase(
    private val weatherRepository: WeatherRepository,
    private val context: CoroutineContext
) {

    val userIdSubject = ReplaySubject.create<Int>()

    suspend fun findWeatherInCitySusp(name: String): WeatherResponse =
        withContext(context) {
            weatherRepository.getWeatherSusp(name)
        }

    fun findWeatherInCity(name: String): Single<WeatherResponse> =
        weatherRepository.getWeather(name)
            .map {
                if (it.cod == 1) userIdSubject.onNext(it.cod)
                it
            }
            .subscribeOnIoObserveOnMain()
}
