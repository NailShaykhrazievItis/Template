package com.itis.template.data

import android.annotation.SuppressLint
import android.location.Location
import com.google.android.gms.location.FusedLocationProviderClient
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class LocationRepositoryImpl(
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

    @SuppressLint("MissingPermission")
    fun getUserLocationSingle(): Single<Location> = Single.create { emitter ->
        client.lastLocation.addOnSuccessListener {
            if (it != null) {
                emitter.onSuccess(it)
            } else {
                emitter.onError(NullPointerException())
            }
        }.addOnFailureListener {
            emitter.onError(it)
        }
    }
}
