package com.itis.template

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : Fragment(R.layout.fragment_profile) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_prev.setOnClickListener { findNavController().navigate(R.id.action_profileFragment_to_settingsFragment) }
        btn_next.setOnClickListener { findNavController().navigate(R.id.action_profileFragment_to_notificationFragment) }
    }
}
