package com.itis.template.presentation.auth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.itis.template.R
import com.itis.template.di.Injector
import com.itis.template.presentation.auth.signin.SignInFragment
import com.itis.template.utils.viewModels
import javax.inject.Inject

class AuthActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var authComponent: AuthComponent

    private lateinit var authViewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        authComponent = Injector.authComponent()
        authComponent.inject(this)
        authViewModel = viewModels(viewModelFactory)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        supportFragmentManager.beginTransaction()
            .replace(R.id.container, SignInFragment())
            .commit()
    }
}
