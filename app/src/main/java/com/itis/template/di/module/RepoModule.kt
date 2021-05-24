package com.itis.template.di.module

import android.content.Context
import com.google.android.gms.location.FusedLocationProviderClient
import com.itis.template.data.LocationRepositoryImpl
import com.itis.template.data.SharedPrefAuthRepository
import com.itis.template.data.WeatherRepositoryImpl
import com.itis.template.data.api.WeatherApi
import com.itis.template.domain.AuthRepository
import com.itis.template.domain.LocationRepository
import com.itis.template.domain.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RepoModule {

    @Provides
    fun provideLocationRepo(
        client: FusedLocationProviderClient
    ): LocationRepository = LocationRepositoryImpl(client)

    @Provides
    fun provideAuthRepo(
        @ApplicationContext context: Context
    ): AuthRepository = SharedPrefAuthRepository(context)

    @Provides
    fun provideWeatherRepository(
        weatherApi: WeatherApi
    ): WeatherRepository = WeatherRepositoryImpl(weatherApi)
}
