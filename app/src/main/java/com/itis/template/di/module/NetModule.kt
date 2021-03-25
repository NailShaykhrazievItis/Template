package com.itis.template.di.module

import com.itis.template.BuildConfig
import com.itis.template.data.api.LoggingInterceptor
import com.itis.template.data.api.WeatherApi
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named

private const val QUERY_API_KEY = "appid"
private const val QUERY_LANG_KEY = "lang"

@Module
class NetModule {

    @Provides
    fun provideWeatherApi(retrofit: Retrofit): WeatherApi =
        retrofit.create(WeatherApi::class.java)

    @Provides
    @Named("loggingInterceptor")
    fun provideLoggingInterceptor(): Interceptor = LoggingInterceptor()

    @Provides
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
    fun provideClient(
        @Named("apiInterceptor") apiKeyInterceptor: Interceptor,
        @Named("langInterceptor") langInterceptor: Interceptor,
        @Named("loggingInterceptor") loggingInterceptor: Interceptor,
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(apiKeyInterceptor)
            .addInterceptor(loggingInterceptor)
            .addInterceptor(langInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .build()

    // в данном случае можно было писать просто addConverterFactory(GsonConverterFactory.create()), зависимость была вынесена для примера
    @Provides
    fun provideRetrofit(
        client: OkHttpClient,
        factory: Converter.Factory
    ) = Retrofit.Builder()
        .client(client)
        .baseUrl(BuildConfig.API_ENDPOINT)
        .addConverterFactory(factory)
        .build()

    @Provides
    fun provideGsonFactory(): Converter.Factory = GsonConverterFactory.create()
}
