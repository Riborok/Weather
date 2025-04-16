package com.bsuir.weather.utils.mapper

import com.bsuir.weather.data.dto.LocationDTO
import com.bsuir.weather.domain.model.LocationModel

object LocationDTOMapper {
    fun LocationDTO.toModel(): LocationModel {
        return LocationModel(
            latitude = this.latitude,
            longitude = this.longitude,
            name = this.name
        )
    }

    fun LocationModel.toDTO(): LocationDTO {
        return LocationDTO(
            latitude = this.latitude,
            longitude = this.longitude,
            name = this.name
        )
    }
}