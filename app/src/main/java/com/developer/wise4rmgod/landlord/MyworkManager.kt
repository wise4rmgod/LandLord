package com.developer.wise4rmgod.landlord


import android.content.Context
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters

class MyworkManager(context: Context, workerParams: WorkerParameters) : Worker(
    context,
    workerParams
) {
    val EXTRA_OUTPUT_MESSAGE = "outputmessage"

    override fun doWork(): Result {

        val output = Data.Builder()
            .putString(EXTRA_OUTPUT_MESSAGE, "Internet is Available")
            .build()
        return Result.success(output)
    }


}