package com.itis.template.presentation.auth.signup

import android.os.Bundle
import android.view.View
import com.itis.template.R
import com.itis.template.presentation.BaseFragment
import com.itis.template.presentation.auth.AuthViewModel
import com.itis.template.presentation.viewModel

class SignUpFragment : BaseFragment(R.layout.fragment_sign_up) {

    private val authViewModel: AuthViewModel by viewModel()
    private val signUpViewModel: SignUpViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
