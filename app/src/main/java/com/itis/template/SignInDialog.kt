package com.itis.template

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import kotlinx.android.synthetic.main.dialog_sign_in.view.*

class SignInDialog : DialogFragment() {

    private var positiveAction: (String, String) -> Unit = { _, _ -> }
    private var negativeAction: () -> Unit = {}

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view = LayoutInflater.from(context).inflate(R.layout.dialog_sign_in, null, false)
        arguments?.getString(ARG_MESSAGE)?.takeIf { it.isNotEmpty() }?.let {
            view.tv_message.text = it
            view.tv_message.visibility = View.VISIBLE
        }
        view.et_password.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                onPositiveClick(view)
                dismiss()
                true
            } else false
        }
        return AlertDialog.Builder(requireContext())
            .setPositiveButton(R.string.confirm) { _, _ ->
                onPositiveClick(view)
            }
            .setNegativeButton(R.string.cancel) { _, _ ->
                onNegativeClick()
            }
            .setView(view)
            .create()
    }

    private fun onPositiveClick(view: View) {
        positiveAction(view.et_name.text.toString(), view.et_password.text.toString())
    }

    private fun onNegativeClick() {
        negativeAction()
    }

    companion object {

        private const val ARG_MESSAGE = "arg_message"

        fun show(
            fragmentManager: FragmentManager,
            message: String? = "",
            negativeAction: () -> Unit,
            positiveAction: (String, String) -> Unit
        ) = SignInDialog().apply {
            this.positiveAction = positiveAction
            this.negativeAction = negativeAction
            arguments = Bundle().apply {
                putString(ARG_MESSAGE, message)
            }
            show(fragmentManager, SignInDialog::class.java.name)
        }
    }
}
