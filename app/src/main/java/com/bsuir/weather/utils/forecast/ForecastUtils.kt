package com.bsuir.weather.utils.forecast

import com.bsuir.weather.domain.model.HourlyForecastModel
import java.time.LocalTime

object ForecastUtils {
    fun getHourlyForecastSubset(
        hourlyForecastList: List<HourlyForecastModel>,
        hours: Int
    ): List<HourlyForecastModel> {
        val currentHour = LocalTime.now().hour
        val startIndex = hourlyForecastList.indexOfFirst {
            it.time.hour == currentHour
        }.coerceAtLeast(0)

        return hourlyForecastList.subList(
            startIndex,
            (startIndex + hours).coerceAtMost(hourlyForecastList.size)
        )
    }
}