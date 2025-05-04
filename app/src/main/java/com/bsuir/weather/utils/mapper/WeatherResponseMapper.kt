package com.bsuir.weather.utils.mapper

import com.bsuir.weather.data.dto.Current
import com.bsuir.weather.data.dto.Daily
import com.bsuir.weather.data.dto.Hourly
import com.bsuir.weather.data.dto.WeatherResponse
import com.bsuir.weather.domain.model.CurrentForecastModel
import com.bsuir.weather.domain.model.DailyForecastModel
import com.bsuir.weather.domain.model.ForecastModel
import com.bsuir.weather.domain.model.HourlyForecastModel
import com.bsuir.weather.utils.converter.WeatherCodeConverter
import com.bsuir.weather.utils.converter.WeatherWindDirectionConverter
import kotlin.math.roundToInt

object WeatherResponseMapper {
    fun WeatherResponse.toModel(): ForecastModel {
        return ForecastModel(
            currentForecast = current.toModel(),
            hourlyForecasts = hourly.toModels(),
            dailyForecasts = daily.toModels()
        )
    }

    private fun Current.toModel(): CurrentForecastModel {
        return CurrentForecastModel(
            temperature = temperature2m.roundToInt(),
            apparentTemperature = apparentTemperature.roundToInt(),
            relativeHumidity = relativeHumidity2m,
            precipitation = precipitation.roundToInt(),
            cloudCover = cloudCover,
            iconId = WeatherCodeConverter.getWeatherIconId(weatherCode, isDay == 1),
            weatherDescriptionId = WeatherCodeConverter.getWeatherDescriptionId(weatherCode),
            surfacePressure = surfacePressure.roundToInt(),
            pressureMSL = pressureMsl.roundToInt(),
            windSpeed = windSpeed10m.roundToInt(),
            windDirectionId = WeatherWindDirectionConverter.getWindDirectionId(windDirection10m),
            windGusts = windGusts10m.roundToInt(),
            time = time
        )
    }

    private fun Hourly.toModels(): List<HourlyForecastModel> {
        return time.mapIndexed { index, time ->
            HourlyForecastModel(
                temperature = temperature2m[index].roundToInt(),
                iconId = WeatherCodeConverter.getWeatherIconId(weatherCode[index], isDay[index] == 1),
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
}
