package com.itis.template.presentation.auth

import androidx.lifecycle.ViewModel
import com.itis.template.domain.UserInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val userInteractor: UserInteractor
): ViewModel() {

    fun signIn(name: String, pass: String): Boolean {
        return userInteractor.signInUser(name, pass)
    }
}
