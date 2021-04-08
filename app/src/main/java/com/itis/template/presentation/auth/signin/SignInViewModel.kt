package com.itis.template.presentation.auth.signin

import androidx.lifecycle.ViewModel
import com.itis.template.domain.AuthRepository
import com.itis.template.domain.LocationRepository
import javax.inject.Inject

class SignInViewModel @Inject constructor(
    private val locationRepository: LocationRepository,
    private val authRepository: AuthRepository
): ViewModel() {

    val test = "Test: SignInViewModel"
    // бла-бла функционал
}
