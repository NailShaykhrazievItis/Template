package com.itis.template.di.component

import com.itis.template.di.module.AppModule
import com.itis.template.di.module.NetModule
import dagger.Component

@Component(modules = [AppModule::class, NetModule::class])
interface AppComponent {

    fun weatherBuilder(): WeatherSubComponent.Builder

    // при использваонии Сomponent Dependency, зависимости, которые доступны другим компонентом должны быть прописаны явно
    // fun provideContext(): Context

//    @Component.Builder
//    interface Builder {
//
//        @BindsInstance
//        fun application(application: Application): WeatherSubComponent.Builder
//
//        fun build(): AppComponent
//    }
}
