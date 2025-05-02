package com.bsuir.weather.utils.converter

import com.bsuir.weather.R

object WeatherWindDirectionConverter {

    fun getWindDirectionId(directionDegree: Int): Int {
        val sectorSize = 360 / directions.size
        val halfSectorSize = sectorSize / 2
        val index = ((directionDegree + halfSectorSize) % 360 / sectorSize).toInt()
        return directions[index % directions.size]
    }

    private val directions = listOf(
        R.string.wind_direction_n,
        R.string.wind_direction_ne,
        R.string.wind_direction_e,
        R.string.wind_direction_se,
        R.string.wind_direction_s,
        R.string.wind_direction_sw,
        R.string.wind_direction_w,
        R.string.wind_direction_nw,
    )
}