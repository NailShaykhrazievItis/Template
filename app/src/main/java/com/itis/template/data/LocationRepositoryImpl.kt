package com.itis.template.data

import android.annotation.SuppressLint
import android.location.Location
import com.google.android.gms.location.FusedLocationProviderClient
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class LocationRepositoryImpl @Inject constructor(
    private val client: FusedLocationProviderClient
) {

    @SuppressLint("MissingPermission")
    suspend fun getUserLocation(): Location = suspendCancellableCoroutine { continuation ->
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
