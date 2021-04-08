package com.itis.template.presentation.auth

import androidx.lifecycle.ViewModel
import com.itis.template.domain.UserInteractor
import javax.inject.Inject

/*
*  AuthViewModel будет один и тот во всех экранах в рамках одного скоупа.
*  ЖЦ скоупа контролится в onCreate() AuthActivity
*/
class AuthViewModel @Inject constructor(
    private val userInteractor: UserInteractor
): ViewModel() {

    fun signIn(name: String, pass: String): Boolean {
        return userInteractor.signInUser(name, pass)
    }
}
