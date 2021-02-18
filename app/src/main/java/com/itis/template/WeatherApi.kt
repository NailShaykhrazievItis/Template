package com.itis.template

import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("weather?units=metric&lang=ru")
    suspend fun getWeather(
        @Query("q") cityName: String
    ) : WeatherResponse
}
