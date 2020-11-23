package com.itis.template

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.work.WorkManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tv_hello.setOnClickListener {
            scheduleWork()
        }
    }

    private fun scheduleWork() {
        TestWorker.scheduleWork(applicationContext, 0, "TEST").also {
            WorkManager.getInstance(applicationContext).getWorkInfoByIdLiveData(it)
                .observe(this, { info -> // need us [lifecycleOwner] for fragments
                    if (info.state.isFinished) {
                        info.outputData.getString("ARG_T")?.also { it ->
                            Toast.makeText(this, "RESULT: $it", Toast.LENGTH_LONG).show()
                        }
                    }

                })
        }
    }
}
