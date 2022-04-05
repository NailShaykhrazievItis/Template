package com.itis.template.di.module

import com.itis.template.di.scope.ActivityScope
import com.itis.template.presentation.auth.AuthActivity
import com.itis.template.presentation.auth.AuthFragmentBindsModule
import com.itis.template.presentation.auth.AuthModule
import com.itis.template.presentation.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ActivityBindsModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [AuthFragmentBindsModule::class, AuthModule::class])
    fun contributeAuthActivity(): AuthActivity

    @ActivityScope
    @ContributesAndroidInjector()
    fun contributeMainActivity(): MainActivity

}
