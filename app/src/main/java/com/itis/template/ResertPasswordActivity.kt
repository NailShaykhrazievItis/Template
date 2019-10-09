package com.itis.template

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_resert_password.*
import kotlinx.android.synthetic.main.activity_resert_password.view.*

class ResertPasswordActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resert_password)

        button.setOnClickListener{
            LoginActivity.PasswordRepository.password = ti_resert.et_resert.text.toString()
            onBackPressed()
        }
    }
}
