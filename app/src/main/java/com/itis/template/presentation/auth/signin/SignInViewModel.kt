package com.itis.template.presentation.auth.signin

import androidx.lifecycle.ViewModel
import com.itis.template.domain.AuthRepository
import com.itis.template.domain.LocationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val locationRepository: LocationRepository,
    private val authRepository: AuthRepository
): ViewModel() {

    val test = "Test: SignInViewModel"
    // бла-бла функционал
}
