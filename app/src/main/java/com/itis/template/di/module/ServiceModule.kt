package com.itis.template.di.module

import com.itis.template.api.WeatherService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class ServiceModule {

    @Provides
    @Singleton
    fun bindWeathreService(retrofit: Retrofit): WeatherService = retrofit.create(
        WeatherService::class.java)
}
