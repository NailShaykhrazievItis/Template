package com.itis.template.domain

import android.location.Location

interface LocationRepository {

    suspend fun getUserLocation(): Location
}
