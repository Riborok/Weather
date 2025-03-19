package com.bsuir.weather.domain.usecase

import com.bsuir.weather.R
import com.bsuir.weather.domain.model.HourlyForecastModel

class HourlyForecastUseCase {
    fun getHourlyForecastList (): List<HourlyForecastModel> {
        return listOf<HourlyForecastModel>(
            HourlyForecastModel("+14°C", R.drawable.sun, "Облачно", "7:00"),
            HourlyForecastModel("+14°C", R.drawable.cloud_basic, "Облачно", "7:00"),
            HourlyForecastModel("+14°C", R.drawable.moon, "Облачно", "7:00"),
            HourlyForecastModel("+14°C", R.drawable.snow, "Облачно", "7:00"),
            HourlyForecastModel("+14°C", R.drawable.wind, "Облачно", "7:00"),
            HourlyForecastModel("+14°C", R.drawable.cloud_fog, "Облачно", "7:00"),
            HourlyForecastModel("+14°C", R.drawable.cloud_lightning, "Облачно", "7:00"),
            HourlyForecastModel("+14°C", R.drawable.cloud_moon, "Облачно", "7:00"),
            HourlyForecastModel("+14°C", R.drawable.cloud_rain, "Облачно", "7:00"),
            HourlyForecastModel("+14°C", R.drawable.cloud_sun, "Облачно", "7:00"),
            HourlyForecastModel("+14°C", R.drawable.sun, "Облачно", "7:00"),
            HourlyForecastModel("+14°C", R.drawable.cloud_basic, "Облачно", "7:00"),
            HourlyForecastModel("+14°C", R.drawable.cloud_basic, "Облачно", "7:00"),
        )
    }
}