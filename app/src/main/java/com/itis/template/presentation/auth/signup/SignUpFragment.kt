package com.itis.template.presentation.auth.signup

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.itis.template.R
import com.itis.template.presentation.BaseFragment
import com.itis.template.presentation.auth.AuthViewModel

class SignUpFragment : BaseFragment(R.layout.fragment_sign_up) {

    val authViewModel: AuthViewModel by viewModels()
    val signUpViewModel: SignUpViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
