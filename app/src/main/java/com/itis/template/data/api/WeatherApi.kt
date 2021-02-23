package com.itis.template.data.api

import com.itis.template.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("weather?units=metric&lang=ru")
    suspend fun getWeather(
        @Query("q") cityName: String
    ) : WeatherResponse
}
