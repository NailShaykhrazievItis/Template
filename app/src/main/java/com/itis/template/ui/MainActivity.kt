package com.itis.template.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.itis.template.R
import com.itis.template.model.AppDatabase
import com.itis.template.model.entity.User
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity(), CoroutineScope by MainScope() {

    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        db = AppDatabase(this)

        launch {
            db.userDao().save(User(1, "My Name", 5))
            db.userDao().save(User(2, "And His Name", 50))
            db.userDao().save(User(3, "JOHN CENA!!!!!!!!!", 505, "john@cena.com"))

            tv_hello.text = db.userDao().getUserById(3)?.name ?: "EMPTY"
        }
        initListeners()
    }

    override fun onDestroy() {
        coroutineContext.cancelChildren()
        coroutineContext.cancel()
        super.onDestroy()
    }

    private fun initListeners() {
        btn_next.setOnClickListener { openDetailsPage(2) }
        btn_clear.setOnClickListener { clearDatabase() }
    }

    private fun clearDatabase() {
        launch { db.userDao().deleteAllUsers() }
    }

    private fun openDetailsPage(id: Int) = startActivity(DetailsActivity.createIntent(this, id))
}
