package com.itis.template

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.view.*

class LoginActivity : AppCompatActivity() {

    object PasswordRepository {
        var password: String = "dianchik"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        et_sign_in_pass.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                ti_sign_in_pass.error = null
                ti_sign_in_pass.isErrorEnabled = false
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

        })

        button_sign.setOnClickListener {
            progress_bar.visibility = View.VISIBLE
            if (ti_sign_in_pass.et_sign_in_pass?.text.toString() == PasswordRepository.password) {
                startActivity(Intent(this, MainActivity::class.java))
            } else {
                setPasswordError()
            }
            progress_bar.visibility = View.INVISIBLE
        }

        text_resert.setOnClickListener {
            startActivity(Intent(this, ResertPasswordActivity::class.java))
        }
    }

    private fun setPasswordError() {
        ti_sign_in_pass.error = getString(R.string.validate_password)
    }
}
