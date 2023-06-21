package com.allen.weather.extension

import android.app.Activity
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat

inline fun Activity.isPermissionGranted(permissions: List<String>): Boolean =
    permissions.firstOrNull { ActivityCompat.checkSelfPermission(this, it) != PackageManager.PERMISSION_GRANTED }
        ?.let { false }
        ?: true
