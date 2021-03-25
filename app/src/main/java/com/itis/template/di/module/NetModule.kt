package com.itis.template.di.module

import com.itis.template.BuildConfig
import com.itis.template.data.api.LoggingInterceptor
import com.itis.template.data.api.WeatherApi
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

private const val QUERY_API_KEY = "appid"
private const val QUERY_LANG_KEY = "lang"

@Module
class NetModule {

    @Provides
    @Singleton
    fun provideWeatherApi(retrofit: Retrofit): WeatherApi = retrofit.create(WeatherApi::class.java)

    @Provides
    @Singleton
    @Named("loggingInterceptor")
    fun provideLoggingInterceptor(): Interceptor = LoggingInterceptor()

    @Provides
    @Singleton
    @Named("apiInterceptor")
    fun provideApiKeyInterceptor(): Interceptor = Interceptor { chain ->
        val original = chain.request()
        original.url().newBuilder()
            .addQueryParameter(QUERY_API_KEY, BuildConfig.API_KEY)
            .build()
            .let {
                chain.proceed(
                    original.newBuilder().url(it).build()
                )
            }
    }

    @Provides
    @Singleton
    @Named("langInterceptor")
    fun provideLangInterceptor(): Interceptor = Interceptor { chain ->
        val original = chain.request()
        original.url().newBuilder()
            .addQueryParameter(QUERY_LANG_KEY, "ru")
            .build()
            .let {
                chain.proceed(
                    original.newBuilder().url(it).build()
                )
            }
    }

    @Provides
    @Singleton
    fun provideClient(
        @Named("apiInterceptor") apiKeyInterceptor: Interceptor,
        @Named("langInterceptor") langInterceptor: Interceptor,
        @Named("loggingInterceptor") loggingInterceptor: Interceptor,
    ): OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(apiKeyInterceptor)
            .addInterceptor(loggingInterceptor)
            .addInterceptor(langInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .build()

    @Provides
    @Singleton
    fun provideRetrofit(
        client: OkHttpClient,
    ): Retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl(BuildConfig.API_ENDPOINT)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}
