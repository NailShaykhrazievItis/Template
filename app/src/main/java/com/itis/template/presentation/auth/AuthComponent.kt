package com.itis.template.presentation.auth

import com.itis.template.di.ActivityScope
import com.itis.template.presentation.auth.signin.SignInFragment
import com.itis.template.presentation.auth.signin.SignInModule
import com.itis.template.presentation.auth.signup.SignUpFragment
import com.itis.template.presentation.auth.signup.SignUpModule
import dagger.Subcomponent

@Subcomponent(modules = [AuthModule::class, SignInModule::class, SignUpModule::class])
@ActivityScope
interface AuthComponent {

    fun inject(activity: AuthActivity)
    fun inject(fragment: SignUpFragment)
    fun inject(fragment: SignInFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(): AuthComponent
    }
}
