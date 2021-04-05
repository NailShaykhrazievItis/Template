package com.itis.template.data.api

import com.itis.template.WeatherResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("weather?units=metric&lang=ru")
    suspend fun getWeatherSusp(
        @Query("q") cityName: String
    ) : WeatherResponse

    @GET("weather?units=metric&lang=ru")
    fun getWeather(
        @Query("q") cityName: String
    ) : Single<WeatherResponse>
}
