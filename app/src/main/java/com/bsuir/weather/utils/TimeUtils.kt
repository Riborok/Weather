package com.bsuir.weather.utils

object TimeUtils {
    private const val MILLISECONDS_IN_SECOND = 1000L
    private const val MILLISECONDS_IN_MINUTE = 60 * MILLISECONDS_IN_SECOND
    private const val MILLISECONDS_IN_HOUR = 60 * MILLISECONDS_IN_MINUTE
    private const val MILLISECONDS_IN_DAY = 24 * MILLISECONDS_IN_HOUR

    fun daysToMillis(days: Long): Long {
        return days * MILLISECONDS_IN_DAY
    }

    fun hoursToMillis(hours: Long): Long {
        return hours * MILLISECONDS_IN_HOUR
    }

    fun minutesToMillis(minutes: Long): Long {
        return minutes * MILLISECONDS_IN_MINUTE
    }

    fun secondsToMillis(seconds: Long): Long {
        return seconds * MILLISECONDS_IN_SECOND
    }
}
