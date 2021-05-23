package com.itis.template.di

import android.app.Application
import com.itis.template.ApplicationDelegate
import com.itis.template.di.module.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        NetModule::class,
        RepoModule::class,
        ViewModelFactoryModule::class,
        WeatherModule::class,
        ActivityBindsModule::class
    ]
)
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(application: ApplicationDelegate)
}
