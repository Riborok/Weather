package com.bsuir.weather.utils.ext

import kotlinx.datetime.toJavaLocalDateTime
import java.time.ZoneOffset

fun kotlinx.datetime.LocalDateTime.toEpochMillisUTC(): Long =
    this.toJavaLocalDateTime().toEpochMillisUTC()

fun java.time.LocalDateTime.toEpochMillisUTC(): Long =
    this.toInstant(ZoneOffset.UTC).toEpochMilli()