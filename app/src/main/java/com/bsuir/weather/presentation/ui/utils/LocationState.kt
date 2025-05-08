package com.bsuir.weather.presentation.ui.utils

import androidx.compose.runtime.Composable
import com.bsuir.weather.domain.model.LocationModel
import com.bsuir.weather.presentation.state.LocationState

@Composable
fun LocationState.OnSuccess(block: @Composable (LocationModel) -> Unit) {
    if (this is LocationState.Success) {
        block(location)
    }
}

@Composable
fun LocationState.OnNoContent(block: @Composable () -> Unit) {
    if (this is LocationState.NoContent) {
        block()
    }
}
