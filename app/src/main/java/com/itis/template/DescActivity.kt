package com.itis.template

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_desc.*

class DescActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_desc)
        val book = intent?.extras?.getString(KEY_BOOK) ?: "DEFAULT NAME"
        tv_author.text = book
    }

    companion object {
        private const val KEY_BOOK = "bookd"

        fun createIntent(activity: Activity, author: String) =
            Intent(activity, DescActivity::class.java).apply {
                putExtra(KEY_BOOK, author)
            }
    }
}
