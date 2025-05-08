package com.bsuir.weather.exception

import java.io.IOException

class AddressNotFoundException(message: String, val placeId: String) :
    IOException("$placeId - $message")
