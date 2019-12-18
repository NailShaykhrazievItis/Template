package com.itis.template.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.itis.template.R
import com.itis.template.model.AppDatabase
import com.itis.template.model.entity.User
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext
/*
* Этот класс пример - так не стоит писать. Пример того как самому имплементировать корутин скоуп.
* Пример того как переключать контекст. То как стоит писать в MainActivity
*/
class DetailsActivity : AppCompatActivity(), CoroutineScope {

    private lateinit var db: AppDatabase

    override val coroutineContext: CoroutineContext = SupervisorJob() + Dispatchers.Default

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        db = AppDatabase(this)
        launch(Dispatchers.Main) {
            val id = intent?.extras?.getInt(EXTRA_ID) ?: -1
            getUserById(id)?.also {
                tv_age.text = it.age.toString()
                tv_name.text = it.name
            }
        }
    }

    override fun onDestroy() {
        coroutineContext.cancelChildren()
        coroutineContext.cancel()
        super.onDestroy()
    }

    private suspend fun getUserById(id: Int): User? = withContext(Dispatchers.IO) {
        db.userDao().getUserById(id)
    }

    companion object {

        private const val EXTRA_ID = "EXTRA_ID"

        fun createIntent(activity: Activity, id: Int): Intent =
            Intent(activity, DetailsActivity::class.java).apply {
                putExtra(EXTRA_ID, id)
            }
    }
}
