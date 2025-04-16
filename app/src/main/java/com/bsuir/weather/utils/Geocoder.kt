package com.bsuir.weather.utils

import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.location.Geocoder.GeocodeListener
import android.os.Build

object Geocoder {
    fun Context.getCityName(
        latitude: Double,
        longitude: Double,
        onResult: (Address?) -> Unit,
        onError: (Exception) -> Unit = {}
    ) {
        val geocoder = Geocoder(this)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            geocoder.getFromLocation(latitude, longitude, 1, object : GeocodeListener {
                override fun onGeocode(addresses: MutableList<Address>) {
                    if (addresses.isNotEmpty()) {
                        val cityName = addresses[0]
                        onResult(cityName)
                    } else {
                        onResult(null)
                    }
                }
                override fun onError(errorMessage: String?) {
                    onError(Exception(errorMessage))
                }
            })
        } else {
            try {
                @Suppress("DEPRECATION")
                val addresses = geocoder.getFromLocation(latitude, longitude, 1)
                if (!addresses.isNullOrEmpty()) {
                    val cityName = addresses[0]
                    onResult(cityName)
                } else {
                    onResult(null)
                }
            } catch (e: Exception) {
                onError(e)
            }
        }
    }
}
