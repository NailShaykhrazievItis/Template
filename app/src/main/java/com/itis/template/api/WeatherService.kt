package com.itis.template.api

import com.itis.template.response.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("weather")
    suspend fun weatherByName(@Query("q") name: String): WeatherResponse
}

