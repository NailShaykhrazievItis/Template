package com.itis.template

import android.content.Context
import android.util.Log
import androidx.work.*
import java.util.*
import java.util.concurrent.TimeUnit

class TestWorker(
    context: Context,
    params: WorkerParameters
) : Worker(context, params) {

    override fun doWork(): Result =
        inputData.getString(KEY_B).takeUnless { it.isNullOrEmpty() }?.let {
            Log.e("TestWorker", "Hello Михаил: $it")

            label@run {

            }
            Result.success(workDataOf("ARG_T" to "iOS the best platform"))
        } ?: Result.failure()

    companion object {

        private const val KEY_A = "key_a"
        private const val KEY_B = "key_flex"

        fun scheduleWork(context: Context, a: Int, b: String): UUID {
            val data = workDataOf(
                KEY_A to a,
                KEY_B to b
            )
            val constraint = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build()
            val request = OneTimeWorkRequestBuilder<TestWorker>()
                .setConstraints(constraint)
                .setInputData(data)
                .build()
            return start(context, request)
        }

        fun schedulePeriodicWork(context: Context): UUID {
            val constraint = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .setRequiresBatteryNotLow(true)
                .build()
            val periodic = PeriodicWorkRequestBuilder<TestWorker>(2, TimeUnit.HOURS)
                .setConstraints(constraint)
                .build()
            return start(context, periodic)
        }

        private fun start(context: Context, request: WorkRequest): UUID {
            WorkManager.getInstance(context).enqueue(request)
            return request.id
        }
    }
}
