package com.itis.template.presentation.auth.signin

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.itis.template.R
import com.itis.template.presentation.auth.AuthActivity
import com.itis.template.presentation.auth.AuthViewModel
import com.itis.template.utils.viewModels
import kotlinx.android.synthetic.main.fragment_sign_in.*
import javax.inject.Inject

class SignInFragment : Fragment(R.layout.fragment_sign_in) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var authViewModel: AuthViewModel
    private lateinit var signInViewModel: SignInViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (activity as AuthActivity).authComponent.inject(this)
        authViewModel = viewModels(viewModelFactory)
        signInViewModel = viewModels(viewModelFactory)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_sign_in.setOnClickListener {
            authViewModel.signIn("QWER", "pass").also {
                showToast("Result is: $it")
            }
        }
        showToast(signInViewModel.test)
    }

    private fun showToast(text: String) {
        Toast.makeText(requireContext(), text, Toast.LENGTH_LONG).show()
    }
}
