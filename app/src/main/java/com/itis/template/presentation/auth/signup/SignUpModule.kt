package com.itis.template.presentation.auth.signup

import androidx.lifecycle.ViewModel
import com.itis.template.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface SignUpModule {

    @Binds
    @IntoMap
    @ViewModelKey(SignUpViewModel::class)
    fun bindSignUpViewModel(viewModel: SignUpViewModel): ViewModel
}
