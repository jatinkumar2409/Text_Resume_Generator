package com.example.textres.Location

import android.annotation.SuppressLint
import android.content.Context
import android.location.LocationRequest
import android.os.Looper
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow

class LocationFlow(context: Context) {
    val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
    val locationRequest = com.google.android.gms.location.LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY , 5000L).build()

    @SuppressLint("MissingPermission")
    fun getLocation() = callbackFlow<Coordinates>{
val callback: LocationCallback = object : LocationCallback(){
    override fun onLocationResult(lr: LocationResult) {
        super.onLocationResult(lr)
        lr.locations.last().let { it ->
            trySend(Coordinates(latitude = it.latitude , longitude = it.longitude))
        }
    }
}
       fusedLocationClient.requestLocationUpdates(locationRequest , callback , Looper.getMainLooper())
awaitClose{ fusedLocationClient.removeLocationUpdates(callback)}
    }
}

data class Coordinates(val latitude : Double , val longitude : Double)