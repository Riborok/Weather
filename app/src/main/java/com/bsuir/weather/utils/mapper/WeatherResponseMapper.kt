package com.bsuir.weather.utils.mapper

import com.bsuir.weather.data.dto.Current
import com.bsuir.weather.data.dto.Daily
import com.bsuir.weather.data.dto.Hourly
import com.bsuir.weather.data.dto.WeatherResponse
import com.bsuir.weather.domain.model.CurrentForecastModel
import com.bsuir.weather.domain.model.DailyForecastModel
import com.bsuir.weather.domain.model.ForecastModel
import com.bsuir.weather.domain.model.HourlyForecastModel
import kotlinx.datetime.LocalDateTime
import kotlin.math.roundToInt

object WeatherResponseMapper {
    fun WeatherResponse.toModel(): ForecastModel {
        val sunTimes = SunTimes(daily)
        return ForecastModel(
            currentForecastModel = current.toModel(sunTimes),
            hourlyForecastModels = hourly.toModels(sunTimes),
            dailyForecastModels = daily.toModels()
        )
    }

    private fun Current.toModel(sunTimes: SunTimes): CurrentForecastModel {
        val isDay = sunTimes.isDaytime(time)
        return CurrentForecastModel(
            temperature = temperature2m.roundToInt(),
            apparentTemperature = apparentTemperature.roundToInt(),
            iconId = WeatherCodeConverter.getWeatherIconId(weatherCode, isDay),
            weatherDescriptionId = WeatherCodeConverter.getWeatherDescriptionId(weatherCode),
            windSpeed = windSpeed10m.roundToInt(),
            windDirection = convertWindDirection(windDirection10m),
            surfacePressure = surfacePressure.roundToInt(),
            relativeHumidity = relativeHumidity2m,
            time = time
        )
    }

    private fun Hourly.toModels(sunTimes: SunTimes): List<HourlyForecastModel> {
        return time.mapIndexed { index, time ->
            val isDay = sunTimes.isDaytime(time)
            HourlyForecastModel(
                temperature = temperature2m[index].roundToInt(),
                iconId = WeatherCodeConverter.getWeatherIconId(weatherCode[index], isDay),
                weatherDescriptionId = WeatherCodeConverter.getWeatherDescriptionId(weatherCode[index]),
                time = time
            )
        }
    }

    private fun Daily.toModels(): List<DailyForecastModel> {
        return date.mapIndexed { index, date ->
            DailyForecastModel(
                iconId = WeatherCodeConverter.getWeatherIconId(weatherCode[index], true),
                weatherDescriptionId = WeatherCodeConverter.getWeatherDescriptionId(weatherCode[index]),
                minTemperature = temperature2mMin[index].roundToInt(),
                maxTemperature = temperature2mMax[index].roundToInt(),
                sunrise = sunrise[index],
                sunset = sunset[index],
                date = date
            )
        }
    }

    private class SunTimes(daily: Daily) {
        private val dailySunTimes = daily.date.mapIndexed { index, date ->
            date to (daily.sunrise[index] to daily.sunset[index])
        }.toMap()

        fun isDaytime(time: LocalDateTime): Boolean {
            val date = time.date
            val (sunrise, sunset) = dailySunTimes[date] ?: return true
            return time > sunrise && time < sunset
        }
    }

    private val directions = listOf(
        "С", "С-В", "В", "Ю-В", "Ю", "Ю-З", "З", "С-З"
    )

    private fun convertWindDirection(directionDegree: Int): String {
        val sectorSize = 360 / directions.size
        val halfSectorSize = sectorSize / 2
        val index = ((directionDegree + halfSectorSize) % 360 / sectorSize).toInt()
        return directions[index % directions.size]
    }
}
