package com.bsuir.weather.domain.usecase

import com.bsuir.weather.R
import com.bsuir.weather.domain.model.DailyForecastModel

class DailyForecastUseCase {
    fun getHourlyForecastList (): List<DailyForecastModel> {
        return listOf<DailyForecastModel>(
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