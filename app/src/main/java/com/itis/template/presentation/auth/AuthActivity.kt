package com.itis.template.presentation.auth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.itis.template.R
import com.itis.template.di.Injector
import com.itis.template.presentation.auth.signin.SignInFragment
import javax.inject.Inject

class AuthActivity : AppCompatActivity() {

    lateinit var authComponent: AuthComponent

    @Inject
    lateinit var authViewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        authComponent = Injector.authComponent()
        authComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        supportFragmentManager.beginTransaction()
            .replace(R.id.container, SignInFragment())
            .commit()
    }
}
