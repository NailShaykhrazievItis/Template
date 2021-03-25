package com.itis.template.di.component

import android.app.Application
import com.itis.template.di.module.AppModule
import com.itis.template.di.module.NetModule
import com.itis.template.di.module.RepoModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetModule::class, RepoModule::class])
interface AppComponent {

    fun weatherComponent(): WeatherSubComponent.Builder

    // при использваонии Сomponent Dependency, зависимости, которые доступны другим компонентом должны быть прописаны явно. Example:
    // fun provideContext(): Context

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}
