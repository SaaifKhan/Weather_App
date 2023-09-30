package com.saif.weather_app.data.repository

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.tasks.CancellationTokenSource
import com.saif.weather_app.domain.repository.LocationRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

@Singleton
class LocationRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
) : LocationRepository {
    @SuppressLint("MissingPermission")
    override fun getCurrentLocation(): Flow<LatLng> = flow {
        // Just want to get location only want.
        // If you want to emit the value every time `addOnSuccessListener()` return, you could using `callBackFlow` instead `suspendCancelableCoroutine`.
        emit(suspendCancellableCoroutine { cancellableContinuation ->
            val fusedLocationProviderClient =
                LocationServices.getFusedLocationProviderClient(context)

            val cancellationTokenSource = CancellationTokenSource()

            fusedLocationProviderClient.getCurrentLocation(
                Priority.PRIORITY_HIGH_ACCURACY,
                cancellationTokenSource.token,
            ).addOnSuccessListener { location ->
                if (location != null) {
                    cancellableContinuation.resume(LatLng(location.latitude, location.longitude))
                } else {
                    val error =
                        Toast.makeText(context, "Cannot get current Location", Toast.LENGTH_LONG)
                            .show()
                    cancellableContinuation.resumeWithException(Exception(""))
                }
            }.addOnFailureListener { exception ->
                val error =
                    Toast.makeText(context, "${exception.message}", Toast.LENGTH_LONG).show()
                cancellableContinuation.resumeWithException(Exception(""))
            }.addOnCompleteListener {
                cancellableContinuation.cancel()
            }.addOnCanceledListener {
                cancellableContinuation.cancel()
            }

            cancellableContinuation.invokeOnCancellation {
                cancellationTokenSource.cancel()
            }
        })
    }
}
