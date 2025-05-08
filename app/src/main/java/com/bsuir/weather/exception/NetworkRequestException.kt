package com.bsuir.weather.exception

import io.ktor.http.HttpStatusCode
import java.io.IOException

class NetworkRequestException(message: String, val statusCode: HttpStatusCode) :
    IOException("$statusCode - $message")
