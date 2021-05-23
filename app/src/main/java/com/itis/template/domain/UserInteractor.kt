package com.itis.template.domain

import com.itis.template.di.scope.ActivityScope
import javax.inject.Inject

@ActivityScope
class UserInteractor @Inject constructor(
    private val authRepository: AuthRepository
) {

    fun signInUser(name: String, pass: String): Boolean = when {
        name.isEmpty() || pass.isEmpty() -> false
        pass.length < 5 -> false
        else -> authRepository.signIn(name, pass)
    }

}
