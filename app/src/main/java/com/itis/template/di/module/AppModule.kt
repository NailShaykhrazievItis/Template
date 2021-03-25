package com.itis.template.di.module

import android.app.Application
import android.content.Context
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

@Module
class AppModule(
    private val application: Application
) {

    @Provides
    fun provideContext(): Context = application.applicationContext

    @Provides
    fun provideFusedClient(context: Context): FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)

    @Provides
    fun provideCoroutineContext(): CoroutineContext = Dispatchers.IO
}
