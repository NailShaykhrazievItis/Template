package com.itis.template.presentation.auth.signin

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.itis.template.R
import com.itis.template.presentation.auth.AuthActivity
import com.itis.template.presentation.auth.AuthViewModel
import kotlinx.android.synthetic.main.fragment_sign_in.*
import javax.inject.Inject

class SignInFragment: Fragment(R.layout.fragment_sign_in) {

    @Inject
    lateinit var authViewModel: AuthViewModel

    @Inject
    lateinit var signInViewModel: SignInViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (activity as AuthActivity).authComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_sign_in.setOnClickListener {
            authViewModel.signIn("QWER", "pass")
        }
    }
}
