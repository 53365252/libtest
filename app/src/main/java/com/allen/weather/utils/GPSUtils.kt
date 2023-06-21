package com.allen.weather.utils

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.location.Criteria
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.os.Looper
import android.provider.Settings
import androidx.core.app.ActivityCompat
import com.allen.weather.R
import com.allen.weather.extension.isPermissionGranted

class GPSUtils{
    companion object{
        const val LOCATION_REQUEST_CODE = 0x11

        private val permissions =  listOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
        fun isPermissionGranted(activity: Activity) = activity.isPermissionGranted(
            permissions = permissions
        )

        fun requestPermission(activity: Activity) =
            ActivityCompat.requestPermissions(
                activity,
                permissions.toTypedArray(),
                LOCATION_REQUEST_CODE
            )

        @SuppressLint("MissingPermission")
        fun requestLocationUpdate(activity: Activity, onLocationChange: (location: String) -> Unit) {
            val locationManager = activity.getSystemService(Context.LOCATION_SERVICE) as? LocationManager
            if (locationManager != null && isPermissionGranted(activity)) {
                val criteria = Criteria()
                criteria.accuracy = Criteria.ACCURACY_COARSE
                criteria.isCostAllowed = true
                criteria.powerRequirement = Criteria.POWER_HIGH
                locationManager.requestLocationUpdates(
                    100,
                    0f,
                    criteria,
                    object : LocationListener {
                        override fun onLocationChanged(location: Location) {
                            onLocationChange(buildLocationString(location))
                            locationManager.removeUpdates(this)
                        }

                        override fun onStatusChanged(s: String, i: Int, bundle: Bundle) {}
                        override fun onProviderEnabled(s: String) {}
                        override fun onProviderDisabled(s: String) {}
                    },
                    Looper.getMainLooper()
                )
            }
        }

        fun isEnableGPS(context: Context): Boolean {
            val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as? LocationManager
            return  locationManager?.isProviderEnabled(LocationManager.GPS_PROVIDER) ?: false
        }

        fun toggleUserOpenGps(activity: Activity, onOk: ()->Unit) {
            AlertDialog.Builder(activity)
                .setTitle(R.string.open_gps_title)
                .setMessage(R.string.open_gps_msg)
                .setNegativeButton(R.string.cancel) { dialogInterface, _ -> dialogInterface.dismiss() }
                .setPositiveButton(R.string.ok) { dialogInterface, _ ->
                    onOk()
                    dialogInterface.dismiss()
                }
                .setCancelable(true)
                .show()
        }


        private fun buildLocationString(location: Location) =
            location.latitude.toString() + "," + location.longitude
    }
}