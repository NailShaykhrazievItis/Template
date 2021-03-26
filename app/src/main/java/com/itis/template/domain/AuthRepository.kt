package com.itis.template.domain

interface AuthRepository {

    fun signIn(name: String, pass: String): Boolean

    fun signUp(name: String, pass: String)
}
