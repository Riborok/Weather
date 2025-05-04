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
            time = time,

            temperature = temperature2m.roundToInt(),
            apparentTemperature = apparentTemperature.roundToInt(),

            relativeHumidity = relativeHumidity2m,
            precipitation = precipitation.roundToInt(),
            cloudCover = cloudCover,

            surfacePressure = surfacePressure.roundToInt(),
            pressureMSL = pressureMsl.roundToInt(),

            windSpeed = windSpeed10m.roundToInt(),
            windGusts = windGusts10m.roundToInt(),
            windDirectionId = WeatherWindDirectionConverter.getWindDirectionId(windDirection10m),

            iconId = WeatherCodeConverter.getWeatherIconId(weatherCode, isDay == 1),
            weatherDescriptionId = WeatherCodeConverter.getWeatherDescriptionId(weatherCode)
        )
    }

    private fun Hourly.toModels(): List<HourlyForecastModel> {
        return time.mapIndexed { index, time ->
            HourlyForecastModel(
                time = time,

                temperature = temperature2m[index].roundToInt(),
                apparentTemperature = apparentTemperature[index].roundToInt(),
                temperature80m = temperature80m[index].roundToInt(),
                temperature120m = temperature120m[index].roundToInt(),
                temperature180m = temperature180m[index].roundToInt(),

                relativeHumidity = relativeHumidity2m[index],
                precipitation = precipitation[index].roundToInt(),
                precipitationProbability = precipitationProbability[index],
                cloudCover = cloudCover[index],

                surfacePressure = surfacePressure[index].roundToInt(),
                pressureMsl = pressureMsl[index].roundToInt(),

                uvIndex = uvIndex[index],
                uvIndexClearSky = uvIndexClearSky[index],

                visibility = visibility[index].roundToInt(),

                windSpeed10m = windSpeed10m[index].roundToInt(),
                windSpeed80m = windSpeed80m[index].roundToInt(),
                windSpeed120m = windSpeed120m[index].roundToInt(),
                windSpeed180m = windSpeed180m[index].roundToInt(),

                windGusts10m = windGusts10m[index].roundToInt(),

                windDirectionId10m = WeatherWindDirectionConverter.getWindDirectionId(windDirection10m[index]),
                windDirectionId80m = WeatherWindDirectionConverter.getWindDirectionId(windDirection80m[index]),
                windDirectionId120m = WeatherWindDirectionConverter.getWindDirectionId(windDirection120m[index]),
                windDirectionId180m = WeatherWindDirectionConverter.getWindDirectionId(windDirection180m[index]),

                soilTemperature0cm = soilTemperature0cm[index].roundToInt(),
                soilTemperature6cm = soilTemperature6cm[index].roundToInt(),
                soilTemperature18cm = soilTemperature18cm[index].roundToInt(),
                soilTemperature54cm = soilTemperature54cm[index].roundToInt(),

                soilMoisture0to1cm = soilMoisture0to1cm[index],
                soilMoisture1to3cm = soilMoisture1to3cm[index],
                soilMoisture3to9cm = soilMoisture3to9cm[index],
                soilMoisture9to27cm = soilMoisture9to27cm[index],
                soilMoisture27to81cm = soilMoisture27to81cm[index],

                iconId = WeatherCodeConverter.getWeatherIconId(weatherCode[index], isDay[index] == 1),
                weatherDescriptionId = WeatherCodeConverter.getWeatherDescriptionId(weatherCode[index])
            )
        }
    }

    private fun Daily.toModels(): List<DailyForecastModel> {
        return date.mapIndexed { index, date ->
            DailyForecastModel(
                date = date,

                minTemperature = temperature2mMin[index].roundToInt(),
                maxTemperature = temperature2mMax[index].roundToInt(),
                meanTemperature = temperature2mMean[index].roundToInt(),

                sunrise = sunrise[index],
                sunset = sunset[index],
                daylightDuration = daylightDuration[index].roundToInt(),

                precipitationSum = precipitationSum[index].roundToInt(),
                precipitationProbabilityMean = precipitationProbabilityMean[index],

                cloudCoverMean = cloudCoverMean[index],
                cloudCoverMin = cloudCoverMin[index],
                cloudCoverMax = cloudCoverMax[index],

                relativeHumidityMean = relativeHumidity2mMean[index],

                surfacePressureMean = surfacePressureMean[index].roundToInt(),
                surfacePressureMin = surfacePressureMin[index].roundToInt(),
                surfacePressureMax = surfacePressureMax[index].roundToInt(),

                pressureMslMean = pressureMslMean[index].roundToInt(),
                pressureMslMin = pressureMslMin[index].roundToInt(),
                pressureMslMax = pressureMslMax[index].roundToInt(),

                visibilityMean = visibilityMean[index].roundToInt(),
                visibilityMin = visibilityMin[index].roundToInt(),
                visibilityMax = visibilityMax[index].roundToInt(),

                uvIndexMax = uvIndexMax[index],
                uvIndexClearSkyMax = uvIndexClearSkyMax[index],

                windSpeed10mMin = windSpeed10mMin[index].roundToInt(),
                windSpeed10mMean = windSpeed10mMean[index].roundToInt(),
                windSpeed10mMax = windSpeed10mMax[index].roundToInt(),

                windGusts10mMin = windGusts10mMin[index].roundToInt(),
                windGusts10mMean = windGusts10mMean[index].roundToInt(),
                windGusts10mMax = windGusts10mMax[index].roundToInt(),

                windDirectionId10mDominant = WeatherWindDirectionConverter.getWindDirectionId(
                    windDirection10mDominant[index]
                ),

                iconId = WeatherCodeConverter.getWeatherIconId(weatherCode[index], true),
                weatherDescriptionId = WeatherCodeConverter.getWeatherDescriptionId(weatherCode[index])
            )
        }
    }
}
