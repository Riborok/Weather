package com.bsuir.weather.presentation.ui.utils

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat

@Composable
fun RequestLocationPermission(
    onPermissionResult: (Boolean) -> Unit = {}
) {
    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            onPermissionResult(isGranted)
        }
    )

    LaunchedEffect(Unit) {
        val permissionStatus = ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        )
        if (permissionStatus != PackageManager.PERMISSION_GRANTED) {
            launcher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        } else {
            onPermissionResult(true)
        }
    }
}
