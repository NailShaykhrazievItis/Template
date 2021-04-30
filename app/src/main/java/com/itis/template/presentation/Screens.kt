package com.itis.template.presentation

import android.content.Context
import android.content.Intent
import com.itis.template.presentation.login.LoginActivity
import ru.terrakok.cicerone.android.support.SupportAppScreen

object Screens {

    object LoginScreen : SupportAppScreen() {
        override fun getActivityIntent(context: Context): Intent {
            return Intent(context, LoginActivity::class.java)
        }
    }

    object ProfileScreen : SupportAppScreen() {

    }
}
