package com.itis.template.presentation.auth.signup

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.itis.template.R
import com.itis.template.presentation.auth.AuthActivity
import com.itis.template.presentation.auth.AuthViewModel
import javax.inject.Inject

class SignUpFragment : Fragment(R.layout.fragment_sign_up) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val authViewModel: AuthViewModel =
        ViewModelProvider(this, viewModelFactory).get(AuthViewModel::class.java)
    private val signUpViewModel: SignUpViewModel =
        ViewModelProvider(this, viewModelFactory).get(SignUpViewModel::class.java)

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (activity as AuthActivity).authComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
