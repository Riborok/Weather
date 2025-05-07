package com.bsuir.weather.utils.mapper

import com.bsuir.weather.data.dto.CurrentForecastDTO
import com.bsuir.weather.data.dto.DailyForecastDTO
import com.bsuir.weather.data.dto.ForecastDTO
import com.bsuir.weather.data.dto.HourlyForecastDTO
import com.bsuir.weather.domain.model.CurrentForecastModel
import com.bsuir.weather.domain.model.DailyForecastModel
import com.bsuir.weather.domain.model.ForecastModel
import com.bsuir.weather.domain.model.HourlyForecastModel

object ForecastMapper {
    fun ForecastDTO.toModel(): ForecastModel {
        return ForecastModel(
            currentForecast = currentForecast.toModel(),
            hourlyForecasts = hourlyForecasts.map { it.toModel() },
            dailyForecasts = dailyForecasts.map { it.toModel() }
        )
    }

    fun ForecastModel.toDTO(): ForecastDTO {
        return ForecastDTO(
            currentForecast = currentForecast.toDTO(),
            hourlyForecasts = hourlyForecasts.map { it.toDTO() },
            dailyForecasts = dailyForecasts.map { it.toDTO() }
        )
    }

    fun CurrentForecastDTO.toModel(): CurrentForecastModel {
        return CurrentForecastModel(
            time = time,
            temperature = temperature,
            apparentTemperature = apparentTemperature,
            relativeHumidity = relativeHumidity,
            precipitation = precipitation,
            cloudCover = cloudCover,
            surfacePressure = surfacePressure,
            pressureMSL = pressureMSL,
            windSpeed = windSpeed,
            windGusts = windGusts,
            windDirectionId = windDirectionId,
            iconId = iconId,
            weatherDescriptionId = weatherDescriptionId
        )
    }

    fun CurrentForecastModel.toDTO(): CurrentForecastDTO {
        return CurrentForecastDTO(
            time = time,
            temperature = temperature,
            apparentTemperature = apparentTemperature,
            relativeHumidity = relativeHumidity,
            precipitation = precipitation,
            cloudCover = cloudCover,
            surfacePressure = surfacePressure,
            pressureMSL = pressureMSL,
            windSpeed = windSpeed,
            windGusts = windGusts,
            windDirectionId = windDirectionId,
            iconId = iconId,
            weatherDescriptionId = weatherDescriptionId
        )
    }

    fun HourlyForecastDTO.toModel(): HourlyForecastModel {
        return HourlyForecastModel(
            time = time,
            temperature = temperature,
            apparentTemperature = apparentTemperature,
            temperature80m = temperature80m,
            temperature120m = temperature120m,
            temperature180m = temperature180m,
            relativeHumidity = relativeHumidity,
            precipitation = precipitation,
            precipitationProbability = precipitationProbability,
            cloudCover = cloudCover,
            surfacePressure = surfacePressure,
            pressureMsl = pressureMsl,
            uvIndex = uvIndex,
            uvIndexClearSky = uvIndexClearSky,
            visibility = visibility,
            windSpeed10m = windSpeed10m,
            windSpeed80m = windSpeed80m,
            windSpeed120m = windSpeed120m,
            windSpeed180m = windSpeed180m,
            windGusts10m = windGusts10m,
            windDirectionId10m = windDirectionId10m,
            windDirectionId80m = windDirectionId80m,
            windDirectionId120m = windDirectionId120m,
            windDirectionId180m = windDirectionId180m,
            soilTemperature0cm = soilTemperature0cm,
            soilTemperature6cm = soilTemperature6cm,
            soilTemperature18cm = soilTemperature18cm,
            soilTemperature54cm = soilTemperature54cm,
            soilMoisture0to1cm = soilMoisture0to1cm,
            soilMoisture1to3cm = soilMoisture1to3cm,
            soilMoisture3to9cm = soilMoisture3to9cm,
            soilMoisture9to27cm = soilMoisture9to27cm,
            soilMoisture27to81cm = soilMoisture27to81cm,
            iconId = iconId,
            weatherDescriptionId = weatherDescriptionId
        )
    }

    fun HourlyForecastModel.toDTO(): HourlyForecastDTO {
        return HourlyForecastDTO(
            time = time,
            temperature = temperature,
            apparentTemperature = apparentTemperature,
            temperature80m = temperature80m,
            temperature120m = temperature120m,
            temperature180m = temperature180m,
            relativeHumidity = relativeHumidity,
            precipitation = precipitation,
            precipitationProbability = precipitationProbability,
            cloudCover = cloudCover,
            surfacePressure = surfacePressure,
            pressureMsl = pressureMsl,
            uvIndex = uvIndex,
            uvIndexClearSky = uvIndexClearSky,
            visibility = visibility,
            windSpeed10m = windSpeed10m,
            windSpeed80m = windSpeed80m,
            windSpeed120m = windSpeed120m,
            windSpeed180m = windSpeed180m,
            windGusts10m = windGusts10m,
            windDirectionId10m = windDirectionId10m,
            windDirectionId80m = windDirectionId80m,
            windDirectionId120m = windDirectionId120m,
            windDirectionId180m = windDirectionId180m,
            soilTemperature0cm = soilTemperature0cm,
            soilTemperature6cm = soilTemperature6cm,
            soilTemperature18cm = soilTemperature18cm,
            soilTemperature54cm = soilTemperature54cm,
            soilMoisture0to1cm = soilMoisture0to1cm,
            soilMoisture1to3cm = soilMoisture1to3cm,
            soilMoisture3to9cm = soilMoisture3to9cm,
            soilMoisture9to27cm = soilMoisture9to27cm,
            soilMoisture27to81cm = soilMoisture27to81cm,
            iconId = iconId,
            weatherDescriptionId = weatherDescriptionId
        )
    }

    fun DailyForecastDTO.toModel(): DailyForecastModel {
        return DailyForecastModel(
            date = date,
            iconId = iconId,
            weatherDescriptionId = weatherDescriptionId,

            minTemperature = minTemperature,
            maxTemperature = maxTemperature,
            meanTemperature = meanTemperature,

            sunrise = sunrise,
            sunset = sunset,
            daylightDuration = daylightDuration,

            uvIndexMax = uvIndexMax,
            uvIndexClearSkyMax = uvIndexClearSkyMax,

            windSpeed10mMin = windSpeed10mMin,
            windSpeed10mMean = windSpeed10mMean,
            windSpeed10mMax = windSpeed10mMax,

            windGusts10mMin = windGusts10mMin,
            windGusts10mMean = windGusts10mMean,
            windGusts10mMax = windGusts10mMax,

            windDirectionId10mDominant = windDirectionId10mDominant,

            precipitationSum = precipitationSum,
            precipitationProbabilityMean = precipitationProbabilityMean,

            relativeHumidityMean = relativeHumidityMean,

            surfacePressureMin = surfacePressureMin,
            surfacePressureMean = surfacePressureMean,
            surfacePressureMax = surfacePressureMax,

            pressureMslMin = pressureMslMin,
            pressureMslMean = pressureMslMean,
            pressureMslMax = pressureMslMax,

            visibilityMin = visibilityMin,
            visibilityMean = visibilityMean,
            visibilityMax = visibilityMax,

            cloudCoverMin = cloudCoverMin,
            cloudCoverMean = cloudCoverMean,
            cloudCoverMax = cloudCoverMax
        )
    }

    fun DailyForecastModel.toDTO(): DailyForecastDTO {
        return DailyForecastDTO(
            date = date,
            iconId = iconId,
            weatherDescriptionId = weatherDescriptionId,

            minTemperature = minTemperature,
            maxTemperature = maxTemperature,
            meanTemperature = meanTemperature,

            sunrise = sunrise,
            sunset = sunset,
            daylightDuration = daylightDuration,

            uvIndexMax = uvIndexMax,
            uvIndexClearSkyMax = uvIndexClearSkyMax,

            windSpeed10mMin = windSpeed10mMin,
            windSpeed10mMean = windSpeed10mMean,
            windSpeed10mMax = windSpeed10mMax,

            windGusts10mMin = windGusts10mMin,
            windGusts10mMean = windGusts10mMean,
            windGusts10mMax = windGusts10mMax,

            windDirectionId10mDominant = windDirectionId10mDominant,

            precipitationSum = precipitationSum,
            precipitationProbabilityMean = precipitationProbabilityMean,

            relativeHumidityMean = relativeHumidityMean,

            surfacePressureMin = surfacePressureMin,
            surfacePressureMean = surfacePressureMean,
            surfacePressureMax = surfacePressureMax,

            pressureMslMin = pressureMslMin,
            pressureMslMean = pressureMslMean,
            pressureMslMax = pressureMslMax,

            visibilityMin = visibilityMin,
            visibilityMean = visibilityMean,
            visibilityMax = visibilityMax,

            cloudCoverMin = cloudCoverMin,
            cloudCoverMean = cloudCoverMean,
            cloudCoverMax = cloudCoverMax
        )
    }
}