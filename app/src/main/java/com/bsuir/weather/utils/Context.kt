package com.bsuir.weather.utils

import android.content.Context
import com.bsuir.weather.WeatherApplication

val Context.weatherAppContext: WeatherApplication
    get() = this.applicationContext as WeatherApplication
