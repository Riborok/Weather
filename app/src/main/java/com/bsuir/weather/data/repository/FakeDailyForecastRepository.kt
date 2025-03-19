package com.bsuir.weather.data.repository

import com.bsuir.weather.R
import com.bsuir.weather.domain.model.DailyForecastModel
import com.bsuir.weather.domain.repository.DailyForecastRepository
import javax.inject.Inject

class FakeDailyForecastRepository @Inject constructor() : DailyForecastRepository {
    override fun getDailyForecastList(latitude: Double, longitude: Double): List<DailyForecastModel> {
        return listOf(
            DailyForecastModel("Сегодня", R.drawable.sun, "Облачно", "+14°C", "+14°C"),
            DailyForecastModel("Завтра", R.drawable.cloud_basic, "Облачно", "+14°C", "+14°C"),
            DailyForecastModel("Вт", R.drawable.moon, "Облачно", "+14°C", "+14°C"),
            DailyForecastModel("Ср", R.drawable.snow, "Облачно", "+14°C", "+14°C"),
            DailyForecastModel("Чт", R.drawable.wind, "Облачно", "+14°C", "+14°C"),
            DailyForecastModel("Пт", R.drawable.cloud_fog, "Облачно", "+14°C", "+14°C"),
            DailyForecastModel("Сб", R.drawable.cloud_lightning, "Облачно", "+14°C", "+14°C"),
        )
    }
}
