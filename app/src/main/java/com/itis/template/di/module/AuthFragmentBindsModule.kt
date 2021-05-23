package com.itis.template.di.module

import com.itis.template.di.scope.FragmentScope
import com.itis.template.presentation.auth.signin.SignInFragment
import com.itis.template.presentation.auth.signin.SignInModule
import com.itis.template.presentation.auth.signup.SignUpFragment
import com.itis.template.presentation.auth.signup.SignUpModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface AuthFragmentBindsModule {

    @FragmentScope
    @ContributesAndroidInjector(modules = [SignInModule::class])
    fun contributeSignInFragment(): SignInFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [SignUpModule::class])
    fun contributeSignUpFragment(): SignUpFragment

}
