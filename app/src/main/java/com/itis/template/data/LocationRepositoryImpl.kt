package com.itis.template.data

import android.annotation.SuppressLint
import android.location.Location
import com.google.android.gms.location.FusedLocationProviderClient
import com.itis.template.domain.LocationRepository
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class LocationRepositoryImpl(
    private val client: FusedLocationProviderClient
) : LocationRepository {

    @SuppressLint("MissingPermission")
    override suspend fun getUserLocation(): Location = suspendCancellableCoroutine { continuation ->
        client.lastLocation.addOnSuccessListener {
            if (it != null) {
                continuation.resume(it)
            } else {
                continuation.resumeWithException(NullPointerException())
            }
        }.addOnFailureListener {
            continuation.resumeWithException(it)
        }
    }
}
