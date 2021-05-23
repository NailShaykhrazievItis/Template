package com.itis.template.presentation.auth.signin

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.itis.template.R
import com.itis.template.presentation.auth.AuthViewModel
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_sign_in.*
import javax.inject.Inject

class SignInFragment : DaggerFragment(R.layout.fragment_sign_in) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val authViewModel: AuthViewModel by activityViewModels { viewModelFactory }
    private val signInViewModel: SignInViewModel by viewModels { viewModelFactory }

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
