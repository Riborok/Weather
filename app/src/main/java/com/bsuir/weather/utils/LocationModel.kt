package com.bsuir.weather.utils

import com.bsuir.weather.domain.model.LocationModel

fun LocationModel.deepCopy(): LocationModel {
    return this.copy(
        address = this.address.copy()
    )
}
