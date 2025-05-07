package com.bsuir.weather.widget.utils

import android.content.Context
import com.bsuir.weather.R
import com.bsuir.weather.domain.model.AddressModel
import com.bsuir.weather.utils.ext.formattedOrUnknown
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.toJavaLocalDateTime
import java.time.format.DateTimeFormatter

class WeatherStringFormatter(private val context: Context) {

    fun formatLocation(address: AddressModel): String {
        return address.formattedOrUnknown(context)
    }

    fun formatTemperature(temp: Int): String {
        return str(R.string.degree, temp)
    }

    fun formatFeelsLike(feelsLikeTemp: Int): String {
        return str(R.string.feels_like, formatTemperature(feelsLikeTemp))
    }

    fun formatTitle(location: String, temperature: Int): String {
        return "$location: ${formatTemperature(temperature)}"
    }

    fun formatContentText(feelsLikeTemp: Int, descriptionId: Int): String {
        return "${formatFeelsLike(feelsLikeTemp)}, ${str(descriptionId)}"
    }

    fun formatHour(dateTime: LocalDateTime): String {
        return dateTime.toJavaLocalDateTime().format(DateTimeFormatter.ofPattern("H:mm"))
    }

    fun formatDescription(descriptionId: Int): String {
        return context.getString(descriptionId)
    }

    fun formatWindInfo(windSpeed: Int, windGusts: Int): String {
        return str(
            R.string.wind_info,
            windSpeed,
            windGusts,
            str(R.string.kilometers_per_hour)
        )
    }

    private fun str(resId: Int, vararg args: Any): String {
        return context.getString(resId, *args)
    }
}