package com.itis.template.presentation.auth.signin

import androidx.lifecycle.ViewModel
import com.itis.template.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface SignInModule {

    @Binds
    @IntoMap
    @ViewModelKey(SignInViewModel::class)
    fun bindSignInViewModel(viewModel: SignInViewModel): ViewModel
}
