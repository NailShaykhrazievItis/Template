package com.itis.template

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_chat.*

class ChatFragment : Fragment(R.layout.fragment_chat) {

//    private val args: ChatFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.e("ChatFragment", arguments?.getString("message") ?: "NULL")

        tv_hello.setOnClickListener {
            findNavController().navigate(ChatFragmentDirections.actionChatFragmentToLoginActivity2())
        }

//        Toast.makeText(requireContext(), args.message, Toast.LENGTH_LONG).show()
    }

    companion object {

        fun newInstance(): ChatFragment = ChatFragment().apply {
            arguments = Bundle().apply {
                putInt("ARG_ID", id)
            }
        }

        fun bundleArgs(id: Int) = Bundle().apply {
            putInt("ARG_ID", id)
        }

        fun bundleArgs2(id: Int) = bundleOf("ARG_ID" to id, "ARG_IS_FLAG" to true)
    }
}
