package com.itis.template.di.module

import android.content.Context
import com.google.android.gms.location.FusedLocationProviderClient
import com.itis.template.data.LocationRepositoryImpl
import com.itis.template.data.SharedPrefAuthRepository
import com.itis.template.domain.AuthRepository
import com.itis.template.domain.LocationRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepoModule {

    @Provides
    @Singleton
    fun provideLocationRepo(
        client: FusedLocationProviderClient
    ): LocationRepository = LocationRepositoryImpl(client)

    @Provides
    @Singleton
    fun provideAuthRepo(
        context: Context
    ): AuthRepository = SharedPrefAuthRepository(context)
}
