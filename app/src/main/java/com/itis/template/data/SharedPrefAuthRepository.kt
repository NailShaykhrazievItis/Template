package com.itis.template.data

import android.content.Context
import com.itis.template.domain.AuthRepository

class SharedPrefAuthRepository(context: Context) : AuthRepository {

    private val sharedPreferences = context.getSharedPreferences("Amazing", Context.MODE_PRIVATE)

    override fun signIn(name: String, pass: String): Boolean = name == "Test" && pass == "pass"

    override fun signUp(name: String, pass: String) {
        with(sharedPreferences.edit()) {
            putString("User", name)
            putString("$name#PASS", pass)
            apply()
        }
    }
}
