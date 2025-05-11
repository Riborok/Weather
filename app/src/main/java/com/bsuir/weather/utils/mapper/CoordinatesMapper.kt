package com.bsuir.weather.utils.mapper

import com.bsuir.weather.data.dto.CoordinatesDTO
import com.bsuir.weather.domain.model.Coordinates
import com.google.android.gms.maps.model.LatLng

object CoordinatesMapper {
    fun LatLng.toModel(): Coordinates {
        return Coordinates(
            latitude = this.latitude,
            longitude = this.longitude
        )
    }

    fun Coordinates.toLatLng(): LatLng {
        return LatLng(
            this.latitude,
            this.longitude
        )
    }

    fun Coordinates.toDTO(): CoordinatesDTO {
        return CoordinatesDTO(
            latitude = this.latitude,
            longitude = this.longitude
        )
    }

    fun CoordinatesDTO.toModel(): Coordinates {
        return Coordinates(
            latitude = this.latitude,
            longitude = this.longitude
        )
    }
}