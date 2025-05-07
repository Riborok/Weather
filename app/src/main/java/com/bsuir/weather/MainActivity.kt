package com.bsuir.weather

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.bsuir.weather.presentation.ui.component.Navigation
import com.bsuir.weather.presentation.ui.theme.WeatherTheme
import com.bsuir.weather.widget.WeatherService
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WeatherTheme {
                Navigation()
            }
        }

        startWeatherService()
    }

    private fun startWeatherService() {
        val intent = Intent(this, WeatherService::class.java)
        startForegroundService(intent)
    }
}
