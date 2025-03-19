package com.bsuir.weather.data.repository

import com.bsuir.weather.R
import com.bsuir.weather.domain.model.HourlyForecastModel
import com.bsuir.weather.domain.repository.HourlyForecastRepository
import javax.inject.Inject

class FakeHourlyForecastRepository @Inject constructor() : HourlyForecastRepository {
    override fun getHourlyForecastList(latitude: Double, longitude: Double): List<HourlyForecastModel> {
        return listOf(
            HourlyForecastModel("+14°C", R.drawable.sun, "Облачно", "7:00"),
            HourlyForecastModel("+14°C", R.drawable.cloud_basic, "Облачно", "8:00"),
            HourlyForecastModel("+14°C", R.drawable.moon, "Облачно", "9:00"),
            HourlyForecastModel("+14°C", R.drawable.snow, "Облачно", "10:00"),
            HourlyForecastModel("+14°C", R.drawable.wind, "Облачно", "11:00"),
            HourlyForecastModel("+14°C", R.drawable.cloud_fog, "Облачно", "12:00"),
            HourlyForecastModel("+14°C", R.drawable.cloud_lightning, "Облачно", "13:00"),
            HourlyForecastModel("+14°C", R.drawable.cloud_moon, "Облачно", "14:00"),
            HourlyForecastModel("+14°C", R.drawable.cloud_rain, "Облачно", "15:00"),
            HourlyForecastModel("+14°C", R.drawable.cloud_sun, "Облачно", "16:00"),
            HourlyForecastModel("+14°C", R.drawable.sun, "Облачно", "17:00"),
            HourlyForecastModel("+14°C", R.drawable.cloud_basic, "Облачно", "18:00"),
            HourlyForecastModel("+14°C", R.drawable.cloud_basic, "Облачно", "19:00"),
        )
    }
}
