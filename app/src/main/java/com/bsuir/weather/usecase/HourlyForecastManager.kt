package com.bsuir.weather.usecase

import com.bsuir.weather.model.HourlyForecastInfo
import com.bsuir.weather.R

class HourlyForecastManager {
    fun getHourlyForecastList (): List<HourlyForecastInfo> {
        return listOf<HourlyForecastInfo>(
            HourlyForecastInfo("+14°C", R.drawable.sun, "Облачно", "7:00"),
            HourlyForecastInfo("+14°C", R.drawable.cloud_basic, "Облачно", "7:00"),
            HourlyForecastInfo("+14°C", R.drawable.moon, "Облачно", "7:00"),
            HourlyForecastInfo("+14°C", R.drawable.snow, "Облачно", "7:00"),
            HourlyForecastInfo("+14°C", R.drawable.wind, "Облачно", "7:00"),
            HourlyForecastInfo("+14°C", R.drawable.cloud_fog, "Облачно", "7:00"),
            HourlyForecastInfo("+14°C", R.drawable.cloud_lightning, "Облачно", "7:00"),
            HourlyForecastInfo("+14°C", R.drawable.cloud_moon, "Облачно", "7:00"),
            HourlyForecastInfo("+14°C", R.drawable.cloud_rain, "Облачно", "7:00"),
            HourlyForecastInfo("+14°C", R.drawable.cloud_sun, "Облачно", "7:00"),
            HourlyForecastInfo("+14°C", R.drawable.sun, "Облачно", "7:00"),
            HourlyForecastInfo("+14°C", R.drawable.cloud_basic, "Облачно", "7:00"),
            HourlyForecastInfo("+14°C", R.drawable.cloud_basic, "Облачно", "7:00"),
        )
    }
}