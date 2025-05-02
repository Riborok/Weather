package com.bsuir.weather.utils.converter

import com.bsuir.weather.R

object WeatherCodeConverter {

    fun getWeatherDescriptionId(weatherCode: Int): Int {
        return weatherInfoMap.getValue(weatherCode).descriptionId
    }

    fun getWeatherIconId(weatherCode: Int, isDaytime: Boolean): Int {
        return weatherInfoMap.getValue(weatherCode).iconSelector(isDaytime)
    }

    private class WeatherInfo(
        val descriptionId: Int,
        val iconSelector: (Boolean) -> Int
    )

    private val weatherInfoMap: Map<Int, WeatherInfo> = mapOf(
        0 to WeatherInfo(R.string.weather_clear_sky)
            { isDay -> if (isDay) R.drawable.sun else R.drawable.moon },
        1 to WeatherInfo(R.string.weather_mainly_clear)
            { isDay -> if (isDay) R.drawable.cloud_sun else R.drawable.cloud_moon },
        2 to WeatherInfo(R.string.weather_partly_cloudy)
            { isDay -> if (isDay) R.drawable.cloud_sun else R.drawable.cloud_moon },
        3 to WeatherInfo(R.string.weather_overcast) { _ -> R.drawable.cloud_basic },
        45 to WeatherInfo(R.string.weather_fog) { _ -> R.drawable.cloud_fog },
        48 to WeatherInfo(R.string.weather_rime_fog) { _ -> R.drawable.cloud_fog },
        51 to WeatherInfo(R.string.weather_light_drizzle) { _ -> R.drawable.rain_drop },
        53 to WeatherInfo(R.string.weather_moderate_drizzle) { _ -> R.drawable.rain_drop },
        55 to WeatherInfo(R.string.weather_dense_drizzle) { _ -> R.drawable.rain_drop },
        56 to WeatherInfo(R.string.weather_light_freezing_drizzle) { _ -> R.drawable.rain_drop },
        57 to WeatherInfo(R.string.weather_dense_freezing_drizzle) { _ -> R.drawable.rain_drop },
        61 to WeatherInfo(R.string.weather_light_rain) { _ -> R.drawable.rain_drop },
        63 to WeatherInfo(R.string.weather_moderate_rain) { _ -> R.drawable.rain_drop },
        65 to WeatherInfo(R.string.weather_heavy_rain) { _ -> R.drawable.rain_drop },
        66 to WeatherInfo(R.string.weather_light_freezing_rain) { _ -> R.drawable.rain_drop },
        67 to WeatherInfo(R.string.weather_heavy_freezing_rain) { _ -> R.drawable.rain_drop },
        71 to WeatherInfo(R.string.weather_light_snow) { _ -> R.drawable.snow },
        73 to WeatherInfo(R.string.weather_moderate_snow) { _ -> R.drawable.snow },
        75 to WeatherInfo(R.string.weather_heavy_snow) { _ -> R.drawable.snow },
        77 to WeatherInfo(R.string.weather_snow_grains) { _ -> R.drawable.snow },
        80 to WeatherInfo(R.string.weather_light_rain_showers) { _ -> R.drawable.snow },
        81 to WeatherInfo(R.string.weather_moderate_rain_showers) { _ -> R.drawable.snow },
        82 to WeatherInfo(R.string.weather_heavy_rain_showers) { _ -> R.drawable.snow },
        85 to WeatherInfo(R.string.weather_light_snow_showers) { _ -> R.drawable.snow },
        86 to WeatherInfo(R.string.weather_heavy_snow_showers) { _ -> R.drawable.snow },
        95 to WeatherInfo(R.string.weather_thunderstorm) { _ -> R.drawable.lightning },
        96 to WeatherInfo(R.string.weather_thunderstorm_light_hail) { _ -> R.drawable.lightning },
        99 to WeatherInfo(R.string.weather_thunderstorm_heavy_hail) { _ -> R.drawable.lightning }
    )
}