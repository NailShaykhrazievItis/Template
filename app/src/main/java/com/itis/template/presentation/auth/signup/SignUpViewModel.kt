package com.itis.template.presentation.auth.signup

import androidx.lifecycle.ViewModel
import com.itis.template.domain.LocationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val locationRepository: LocationRepository
): ViewModel() {

    // бла-бла функционал
}
