package com.bsuir.weather.utils.ext

import com.bsuir.weather.domain.model.LocationModel
import com.bsuir.weather.presentation.state.LocationState

fun LocationState.onSuccess(block: (LocationModel) -> Unit) {
    if (this is LocationState.Success) {
        block(location)
    }
}

fun LocationState.onNoContent(block: () -> Unit) {
    if (this is LocationState.NoContent) {
        block()
    }
}
