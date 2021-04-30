package com.itis.template

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(R.layout.fragment_home) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = 34

        val options = NavOptions.Builder()
            .setPopUpTo(R.id.homeFragment, false)
            .setEnterAnim(R.anim.fragment_fade_enter)
            .setExitAnim(R.anim.fragment_fade_exit)
            .build()
        val optionsKtx = navOptions {
            popUpTo(R.id.homeFragment) {
                inclusive = false
            }
            anim {
                enter = R.anim.fragment_fade_enter
                exit = R.anim.fragment_fade_exit
            }
        }

        btn_prev.setOnClickListener {
            findNavController().navigate(
                R.id.action_homeFragment_to_profileFragment,
                ChatFragment.bundleArgs(id),
                options
            )
        }
        btn_next.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToChatFragment(
                chatId = this.id,
                message = "TEST message"
            )

            findNavController().navigate(
                action,
                optionsKtx
            )
        }
    }
}
