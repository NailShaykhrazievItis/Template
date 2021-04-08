package com.itis.template.di.module

import androidx.lifecycle.ViewModelProvider
import com.itis.template.utils.AppViewModelFactory
import dagger.Binds
import dagger.Module

@Module
interface ViewModelFactoryModule {

    @Binds
    fun bindViewModelFactory(factory: AppViewModelFactory): ViewModelProvider.Factory
}
