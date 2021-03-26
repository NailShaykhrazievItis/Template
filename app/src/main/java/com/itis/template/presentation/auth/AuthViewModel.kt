package com.itis.template.presentation.auth

import com.itis.template.domain.UserInteractor
import dagger.assisted.Assisted
import javax.inject.Inject

/*
*  AuthViewModel будет один и тот во всех экранах в рамках одного скоупа.
*  ЖЦ скоупа контролится в onCreate() AuthActivity
*/
class AuthViewModel @Inject constructor(
    private val userInteractor: UserInteractor,
    @Assisted private val userId: Int
) {

    fun signIn(name: String, pass: String) {
        userInteractor.signInUser(name, pass)
    }
}
