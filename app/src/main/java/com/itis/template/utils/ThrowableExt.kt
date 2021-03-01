package com.itis.template.utils

import android.content.res.Resources
import com.itis.template.R
import retrofit2.HttpException
import java.net.UnknownHostException

fun Throwable.getErrorMessage(resources: Resources): String = when {
    this is UnknownHostException -> resources.getString(R.string.no_internet)
    this is HttpException -> response()?.errorBody()?.string() ?: ""
    else -> localizedMessage
}
