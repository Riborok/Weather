package com.bsuir.weather.usecase

import com.bsuir.weather.R
import com.bsuir.weather.model.DailyForecastInfo
import com.bsuir.weather.model.HourlyForecastInfo

class DailyForecastManager {
    fun getHourlyForecastList (): List<DailyForecastInfo> {
        return listOf<DailyForecastInfo>(
            DailyForecastInfo("Сегодня", R.drawable.sun, "Облачно", "+14°C", "+14°C"),
            DailyForecastInfo("Завтра", R.drawable.cloud_basic, "Облачно", "+14°C", "+14°C"),
            DailyForecastInfo("Вт", R.drawable.moon, "Облачно", "+14°C", "+14°C"),
            DailyForecastInfo("Ср", R.drawable.snow, "Облачно", "+14°C", "+14°C"),
            DailyForecastInfo("Чт", R.drawable.wind, "Облачно", "+14°C", "+14°C"),
            DailyForecastInfo("Пт", R.drawable.cloud_fog, "Облачно", "+14°C", "+14°C"),
            DailyForecastInfo("Сб", R.drawable.cloud_lightning, "Облачно", "+14°C", "+14°C"),
        )
    }
}